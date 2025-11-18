package com.ds.compress.ui.edit.viewmodel

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.format.Formatter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.UriUtils
import com.ds.common.base.ItemLayoutData
import com.ds.common.base.viewmodel.BaseViewModel
import com.ds.common.ext.appContext
import com.ds.compress.data.ImageItem
import com.ds.compress.ui.edit.repository.editRepository
import com.ds.res.ResConst
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.destination
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File


/**
 * @Author ljiezhou
 * @date 2023/12/6
 * @Description
 */
class EditImageViewModel : BaseViewModel() {
    //自定义宽
    private var customWidth = 0

    //自定义高
    private var customHeight = 0

    //分辨率相关配置
    val scales = MutableLiveData<ArrayList<ItemLayoutData>>()

    //质量相关配置
    val qualitys = MutableLiveData<ArrayList<ItemLayoutData>>()
    private lateinit var currentImageFile: File

    //当前图片的下标
    var currentPosition = 0

    //当前图片大小信息
    val currentImageSize = MutableLiveData<String>()

    //当前图片宽度
    private var currentImageWidth = 0

    //当前图片高度
    private var currentImageHeight = 0

    //压缩后图片大小信息
    val compressImageSize = MutableLiveData<String>()

    init {
        scales.value = editRepository.scales()
        qualitys.value = editRepository.qualitys()
    }

    val imageItems = MutableLiveData<ArrayList<ImageItem>>()
    fun imageItems(uris: List<Uri>) {
        val tmpImageItems = ArrayList<ImageItem>()
        uris.forEach {
            tmpImageItems.add(ImageItem(it))
        }
        imageItems.value = tmpImageItems
    }

    fun setCustomSize(width: Int, height: Int) {
        this.customWidth = width;
        this.customHeight = height
    }


    fun currentImage(uri: Uri, position: Int) {
        currentPosition = position
        viewModelScope.launch {
            loadingChange.showDialog.value = "loading"
            val bitmap = withContext(Dispatchers.IO) {
                BitmapFactory.decodeStream(appContext.contentResolver.openInputStream(uri))
            }
            currentImageFile = withContext(Dispatchers.IO) {
                UriUtils.uri2File(uri)
            }
            setCurrentImageSize(bitmap.width, bitmap.height)
            bitmap.recycle()
            loadingChange.dismissDialog.value = true
            setParam()
        }
    }

    private fun setCurrentImageSize(width: Int, height: Int) {
        currentImageWidth = width
        currentImageHeight = height
        currentImageSize.value = "${currentImageWidth}x${currentImageHeight}  ${Formatter.formatFileSize(appContext, currentImageFile.length())}"
    }

    val compressItem = MutableLiveData<ImageItem>()


    private lateinit var scaleItem: ItemLayoutData
    private lateinit var qualityItem: ItemLayoutData
    private lateinit var currentItem: ImageItem

    fun setParam() {
        loadingChange.showDialog.value = "loading"
        scaleItem = scales.value!!.first { it.isChecked }
        qualityItem = qualitys.value!!.first { it.isChecked }
        currentItem = imageItems.value?.get(currentPosition) ?: return
        LogUtils.d("scale", scaleItem.value, "quality", qualityItem.value)
        compress()
    }

    val currentCompressPosition = MutableLiveData<Int>()

    /**
     * 单张模式表示预览时的状态
     * @param single Boolean
     */
    private fun compress(single: Boolean = true) {
        if (scaleItem.value == 100 && qualityItem.value == 100) {
            currentItem.compressUri = null
            compressItem.value = currentItem
            compressImageSize.value = ""

            loadingChange.dismissDialog.value = true
            return
        }
        viewModelScope.launch {
            val scale = scaleItem.value.toFloat() / 100F
            val widthScale = scale * currentImageWidth
            val heightScale = scale * currentImageHeight

            val paths = arrayListOf<File>()
            if (single) {
                paths.add(currentImageFile)
            } else {
                imageItems.value?.forEach { item ->
                    paths.add(UriUtils.uri2File(item.uri))
                }
            }
            paths.forEachIndexed { index, file ->
                if (!single)
                    currentCompressPosition.value = index + 1

                val compressFileName = File.separator + "compress" + File.separator + ResConst.APP_NAME + " " + file.name
                val compressFile = File(PathUtils.getExternalAppCachePath() + compressFileName)
                LogUtils.d(compressFile.path)

                val compressedImageFile = Compressor.compress(appContext, file) {
                    destination(compressFile)
                    resolution(widthScale.toInt(), heightScale.toInt())
                    quality(qualityItem.value)
                    format(Bitmap.CompressFormat.JPEG)
                    size((file.length() * (qualityItem.value.toFloat() / 100) * (scaleItem.value.toFloat() / 100)).toLong())
                }
//                val compressResult = withContext(Dispatchers.IO){
//
//                    val compression = Compression().default()
//                    var result = copyToCache(appContext, file)
//                    compression.constraints.forEach { constraint ->
//                        while (constraint.isSatisfied(result).not()) {
//                            result = constraint.satisfy(result)
//                        }
//                    }
//                }

                if (single) {
                    val bitmap = ImageUtils.getBitmap(compressedImageFile)
                    currentItem.compressUri = bitmap
                    compressItem.value = currentItem
                    compressImageSize.value = "${bitmap.width}x${bitmap.height}  ${Formatter.formatFileSize(appContext, compressedImageFile.length())}"
                } else {
                    LogUtils.d(compressFile.name, Formatter.formatFileSize(appContext, compressFile.length()))

                    val destFile = File(PathUtils.getExternalDcimPath() + File.separator + ResConst.APP_NAME + File.separator + compressFile.name)
                    val moveResult = withContext(Dispatchers.IO) {
                        LogUtils.d(destFile)
                        FileUtils.moveFile(compressFile, destFile) { srcFile, destFile ->
                            true
                        }
                    }
                    LogUtils.d(moveResult)
                    notifiyFile(destFile)
                }
            }
            loadingChange.dismissDialog.value = true
            if (!single)
                saveResult.value = true
        }
    }

    private fun notifiyFile(file: File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        mediaScanIntent.data = Uri.fromFile(file)
        appContext.sendBroadcast(mediaScanIntent)
    }

    val saveResult = MutableLiveData<Boolean>()

    val isCompress = MutableLiveData<Boolean>()
    private var isSave = false
    fun save2Album() {
        isSave = true
        isCompress.value = true
        compress(false)
    }

}