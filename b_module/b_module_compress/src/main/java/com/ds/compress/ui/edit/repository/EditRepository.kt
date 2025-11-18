package com.ds.compress.ui.edit.repository

import android.graphics.Bitmap
import android.net.Uri
import android.text.format.Formatter
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.PathUtils
import com.blankj.utilcode.util.ThreadUtils
import com.blankj.utilcode.util.UriUtils
import com.ds.common.base.ItemLayoutData
import com.ds.common.ext.appContext
import com.ds.compress.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File


/**
 * @Author ljiezhou
 * @date 2023/12/9
 * @Description
 */
val editRepository: EditRepository by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
    EditRepository()
}

class EditRepository {

    fun scales(): ArrayList<ItemLayoutData> {
        return arrayListOf(
            ItemLayoutData.Builder().setTitldId(R.string.image_edit_scale_100_title).setDescId(R.string.image_edit_scale_100_desc).setValue(95).isChecked(true).builder(),
            ItemLayoutData.Builder().setTitldId(R.string.image_edit_scale_70_title).setDescId(R.string.image_edit_scale_70_desc).setValue(70).builder(),
            ItemLayoutData.Builder().setTitldId(R.string.image_edit_scale_30_title).setDescId(R.string.image_edit_scale_30_desc).setValue(30).builder(),
//            ItemLayoutData.Builder().setTitldId(R.string.image_edit_scale_custom_title).setDescId(R.string.image_edit_scale_custom_desc).builder(),
        )
    }

    fun qualitys(): ArrayList<ItemLayoutData> {
        return arrayListOf(
            ItemLayoutData.Builder().setTitldId(R.string.image_edit_quality_100_title).setDescId(R.string.image_edit_quality_100_desc).setValue(95).isChecked(true).builder(),
            ItemLayoutData.Builder().setTitldId(R.string.image_edit_quality_70_title).setDescId(R.string.image_edit_quality_70_desc).setValue(80).builder(),
            ItemLayoutData.Builder().setTitldId(R.string.image_edit_quality_30_title).setDescId(R.string.image_edit_quality_30_desc).setValue(50).builder(),
            ItemLayoutData.Builder().setTitldId(R.string.image_edit_quality_custom_title).setDescId(R.string.image_edit_quality_custom_desc).setValue(30).builder(),
        )
    }

    suspend fun toBitmap(uri: Uri): Bitmap = withContext(Dispatchers.IO) {
        val bytes = UriUtils.uri2Bytes(uri)
        ImageUtils.getBitmap(bytes, 0)
    }

    suspend fun getBitmapDesc(tmpBitmap: Bitmap) = withContext(Dispatchers.IO) {
        "${tmpBitmap.width}x${tmpBitmap.height} ${Formatter.formatFileSize(appContext.baseContext, (tmpBitmap.rowBytes * tmpBitmap.height).toLong())}"
    }

    /**
     * 压缩的结果有问题，暂时不使用
     * @param qualityItem ItemLayoutData
     * @param scaleItem ItemLayoutData
     * @param bitmap Bitmap
     * @param size Long
     * @return Pair<Bitmap, Long>
     */
    suspend fun compress(qualityItem: ItemLayoutData, scaleItem: ItemLayoutData, bitmap: Bitmap, size: Long) = withContext(Dispatchers.IO) {
        LogUtils.d(ThreadUtils.isMainThread())
        var bitmap: Bitmap = bitmap

        val tmpFile = File(PathUtils.getExternalAppCachePath() + File.separator + "scaleimage.jpg")

        var fileSize = size
        if (scaleItem.value != 100) {
            val scale = scaleItem.value.toFloat() / 100F
            val widthScale = (scale * bitmap.width).toInt()
            val heightScale = (scale * bitmap.height).toInt()
//            val widthScale = scale * bitmap.width
//            val heightScale = scale * bitmap.height
            LogUtils.d(widthScale, heightScale)
            bitmap = ImageUtils.compressByScale(bitmap, widthScale, heightScale)

//            bitmap = ImageUtils.compressBySampleSize(bitmap,)
//            bitmap = ImageUtils.bytes2Bitmap(ImageUtils.compressByQuality(bitmap, scaleItem.value))
            ImageUtils.save(bitmap, tmpFile, Bitmap.CompressFormat.JPEG)
            LogUtils.d(Formatter.formatFileSize(appContext, tmpFile.length()))

            fileSize = (fileSize * scale).toLong()

        }

        if (qualityItem.value != 100) {
            bitmap = ImageUtils.bytes2Bitmap(ImageUtils.compressByQuality(bitmap, qualityItem.value))

            ImageUtils.save(bitmap, tmpFile, Bitmap.CompressFormat.JPEG, qualityItem.value)
            LogUtils.d(Formatter.formatFileSize(appContext, tmpFile.length()))
            fileSize = (fileSize * (qualityItem.value / 100F)).toLong()

            bitmap = ImageUtils.bytes2Bitmap(ImageUtils.compressByQuality(bitmap, fileSize))

        }

        LogUtils.d(Formatter.formatFileSize(appContext, fileSize))
        bitmap = ImageUtils.bytes2Bitmap(ImageUtils.compressByQuality(bitmap, fileSize))


        ImageUtils.save(bitmap, tmpFile, Bitmap.CompressFormat.JPEG)

        LogUtils.d(Formatter.formatFileSize(appContext, tmpFile.length()))
//        ImageUtils.save2Album(bitmap, Bitmap.CompressFormat.JPEG, qualityItem.value)
        val bitmapSize = tmpFile.length()
        FileUtils.delete(tmpFile)


        Pair(bitmap, bitmapSize)
    }

    /**
     * 得到bitmap的大小
     */
//    open fun getBitmapSize(bitmap: Bitmap): Int {
////        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {     //API 19
////            bitmap.allocationByteCount
////        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) { //API 12
////            bitmap.byteCount
////        } else {
////            bitmap.rowBytes * bitmap.height //earlier versionN V VBVB
////        }
//
////        val bitmap: Bitmap = yourBitmap
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            try {
//                // 使用反射获取 Bitmap 对象的内存占用
//                val clazz: Class<*> = Bitmap::class.java
//                val getAllocationByteCount: Method = clazz.getDeclaredMethod("getAllocationByteCount")
//                getAllocationByteCount.setAccessible(true)
//                val byteCount = getAllocationByteCount.invoke(bitmap) as Int
//                // byteCount 即为 Bitmap 的内存占用，单位字节
//                return byteCount
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//        return 0
//
//    }
}