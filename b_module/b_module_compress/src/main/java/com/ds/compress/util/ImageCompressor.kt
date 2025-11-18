package com.ds.compress.util

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.UriUtils
import com.ds.common.ext.appContext
import com.ds.res.ResConst
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.destination
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import id.zelory.compressor.constraint.resolution
import id.zelory.compressor.constraint.size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class ImageCompressor {

    suspend fun compressImage(uri: Uri, width: Int, height: Int, quality: Int, size: Long): File {
        val currentImageFile = UriUtils.uri2File(uri)

        val compressFileName = StringBuilder()
            .append(PathUtils.getExternalAppCachePath())
            .append(File.separator)
            .append("compress")
            .append(File.separator)
            .append(ResConst.APP_NAME)
            .append(" ")
            .append(currentImageFile.name)
            .toString()

        return Compressor.compress(appContext, currentImageFile) {
            destination(File(compressFileName))
            resolution(width, height)
            quality(quality)
            format(Bitmap.CompressFormat.JPEG)
            size(size)
        }
    }

    suspend fun moveCompressedFile(compressedFile: File, destDir: String): Boolean {
        val destFile = File(destDir + File.separator + compressedFile.name)
        return withContext(Dispatchers.IO) {
            compressedFile.renameTo(destFile)
        }
    }

    fun notifiyFile(file: File) {
        val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
        mediaScanIntent.data = Uri.fromFile(file)
        appContext.sendBroadcast(mediaScanIntent)
    }
}