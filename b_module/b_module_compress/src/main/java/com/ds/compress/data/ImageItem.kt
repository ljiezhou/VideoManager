package com.ds.compress.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import com.blankj.utilcode.util.UriUtils
import com.blankj.utilcode.util.Utils
import java.io.File

/**
 * @Author ljiezhou
 * @date 2023/12/13
 * @Description
 */
class ImageItem(val uri: Uri) {
    var file: File? = null
    var compressUri: Bitmap? = null

    suspend fun uriToFile(): File {
        return file ?: uri.let {
            val result = UriUtils.uri2File(it)
            file = result
            result
        } ?: throw IllegalArgumentException("uri and file are both null")
    }

    suspend fun uriToBitmap(): Bitmap {
        return compressUri ?: uri.let {
            val inputStream = Utils.getApp().contentResolver.openInputStream(it)
            val result = BitmapFactory.decodeStream(inputStream)
            compressUri = result
            result
        } ?: throw IllegalArgumentException("uri and bitmap are both null")
    }
}
