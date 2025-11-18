package com.ds.compress.ui.param.viewmodel

import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PathUtils
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.common.ext.appContext
import com.ds.compress.data.ImageItem
import com.ds.compress.util.ImageCompressor
import com.ds.res.ResConst
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class CompressParamViewModel : BaseViewModel() {

    private val _imageUris = arrayListOf<Uri>()
    private val _totalSize = MutableLiveData<Long>()
    val totalSize: LiveData<Long> get() = _totalSize

    fun calculateImageStats(imageUris: List<Uri>) {
        viewModelScope.launch {
            _imageUris.addAll(imageUris)
            val totalSize = withContext(Dispatchers.IO) {
                imageUris.sumOf { uri -> getImageSize(uri) }
            }
            _totalSize.value = totalSize
        }
    }

    private fun getImageSize(uri: Uri): Long {
        // Simulating logic to get the size of the image from the Uri
        val cursor = appContext.contentResolver.query(uri, null, null, null, null)
        return if (cursor != null && cursor.moveToFirst()) {
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            val size = if (sizeIndex != -1) cursor.getLong(sizeIndex) else 0L
            cursor.close()
            size
        } else {
            0L
        }
    }

    fun scaleCompress(percent: Int, width: Int, height: Int, quality: Int) {
        compressImages(CompressType.DIMENSION, percent, width, height, quality)
    }

    private val imageCompressor = ImageCompressor()

    val progress = MutableLiveData<Int>()
    fun sizeCompress(compressSize: Int) {
        compressImages(CompressType.SIZE, compressSize)
    }


    private fun compressImages(compressType: CompressType, compressSize: Int = 0, percent: Int = 0, width: Int = 0, height: Int = 0, quality: Int = 100) {
        LogUtils.d(compressType, compressSize, percent, width, height, quality)
        return
        if (_imageUris.isEmpty()) {
            LogUtils.d("No images to compress")
            return
        }
        var compressTotal = 0

        progress.postValue(0)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _imageUris.chunked(BATCH_SIZE).forEach { batch ->
                    batch.forEach {
                        val imageItem = ImageItem(it)
                        LogUtils.d("uriToFile")
                        val currentImageFile = imageItem.uriToFile()
                        LogUtils.d("uriToBitmap")
                        val currentImageBitmap = imageItem.uriToBitmap()

                        when (compressType) {
                            CompressType.SIZE -> {
                                val compressSizeInBytes = compressSize * 1024
                                if (currentImageFile.length() <= compressSizeInBytes) {
                                    LogUtils.d("Image is already smaller than the target size, skipping compression")
                                } else {
                                    LogUtils.d("compressImage")
                                    compressImage(currentImageBitmap.width, currentImageBitmap.height, quality, compressSizeInBytes.toLong(), it)
                                }
                            }

                            CompressType.DIMENSION -> {
                                val scale = percent.toFloat() / 100
                                val compressWidth: Int
                                val compressHeight: Int
                                if (scale != 0f) {
                                    compressWidth = (currentImageBitmap.width * scale).toInt()
                                    compressHeight = (currentImageBitmap.height * scale).toInt()
                                } else {
                                    compressWidth = width
                                    compressHeight = height
                                }
                                compressImage(compressWidth, compressHeight, quality, currentImageFile.length() * 100 / quality, it)
                            }
                        }
                        compressTotal++
                        LogUtils.d(((compressTotal.toFloat() / _imageUris.size) * 100).toInt())
                        progress.postValue(((compressTotal.toFloat() / _imageUris.size) * 100).toInt())
                    }
                }
            }
        }
    }

    enum class CompressType {
        SIZE,
        DIMENSION
    }

    private suspend fun compressImage(compressWidth: Int, compressHeight: Int, compressQuality: Int, compressSize: Long, it: Uri) {
        val compressedFile = imageCompressor.compressImage(it, compressWidth, compressHeight, compressQuality, compressSize)
        moveCompressedFile(compressedFile)
    }

    private fun moveCompressedFile(compressedFile: File) {
        LogUtils.d("movecompressedFile")
        val destinationPath = PathUtils.getExternalDcimPath() + File.separator + ResConst.APP_NAME
        val destinationFile = File(destinationPath, compressedFile.name)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, compressedFile.name) // File name
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")           // File type
                put(MediaStore.Images.Media.RELATIVE_PATH, "${Environment.DIRECTORY_DCIM}/${ResConst.APP_NAME}") // Path
            }
            val uri = appContext.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            uri?.let { imageUri ->
                appContext.contentResolver.openOutputStream(imageUri)?.use { outputStream ->
                    // Open FileInputStream to read file content
                    FileInputStream(compressedFile).use { inputStream ->
                        inputStream.copyTo(outputStream ?: throw IOException("Failed to open output stream"))
                    }
                } ?: throw IOException("Failed to get output stream.")
            } ?: throw IOException("Failed to create new MediaStore record.")
        } else {
            LogUtils.d("compressedFile: $compressedFile", "destinationPath: $destinationPath")
            val moveResult = FileUtils.moveFile(compressedFile, File(destinationPath)) { srcFile, destFile -> true }
            if (moveResult) {
                imageCompressor.notifiyFile(destinationFile)
                LogUtils.d(destinationPath)
            } else {
                LogUtils.d("Failed to move file to destination")
            }
        }

    }

    companion object {
        // Batch size for compressing images
        private const val BATCH_SIZE = 10
    }
}