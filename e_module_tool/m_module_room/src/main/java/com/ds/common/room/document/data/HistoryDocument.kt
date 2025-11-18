package com.ds.common.room.document.data

import android.net.Uri
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.blankj.utilcode.util.UriUtils
import com.ds.common.room.document.converter.Converter
import kotlinx.android.parcel.Parcelize
import java.io.File

/**
 * fileName 本地记录名称
 * fileType 文档类型
 * files    选择的文件
 */
@Keep
@Parcelize
@Entity(tableName = "HistoryDocument")
@TypeConverters(Converter::class)
data class HistoryDocument(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo
    val conversionType: Int,
    @ColumnInfo
    val conversionTitle: String,
    @ColumnInfo
    val fileName: String,
    @ColumnInfo
    val fromFilePaths: ArrayList<String> = arrayListOf(),
    @ColumnInfo
    val toFilePaths: ArrayList<String> = arrayListOf()
) : Parcelable {
    companion object {
        fun getNewItemEntry(fileName: String, fromFilePaths: ArrayList<String>, filePaths: ArrayList<String>, documentType: Int, conversionTitle: String): HistoryDocument {
            val files = arrayListOf<String>()
            for (imageUris1 in filePaths) {
                files.add(imageUris1)
            }
            return HistoryDocument(fileName = fileName, fromFilePaths = fromFilePaths, toFilePaths = files, conversionType = documentType, conversionTitle = conversionTitle)
        }

        fun getItem(fileName: String, fromPaths: ArrayList<String>, documentType: Int, conversionTitle: String): HistoryDocument {
            val files = arrayListOf<String>()
            for (imageUris1 in fromPaths) {
                files.add(imageUris1)
            }
            return HistoryDocument(fileName = fileName, fromFilePaths = files, conversionType = documentType, conversionTitle = conversionTitle)
        }

        fun toFilesToUri(historyDocument: HistoryDocument): ArrayList<Uri> {
            val uris = arrayListOf<Uri>()
            for (toFilePath in historyDocument.toFilePaths) {
                uris.add(UriUtils.file2Uri(File(toFilePath)))
            }
            return uris
        }
    }

    override fun toString(): String {
        return "HistoryDocument(id=$id, conversionType=$conversionType, conversionTitle='$conversionTitle', fileName='$fileName', fromFilePaths=$fromFilePaths, toFilePaths=$toFilePaths)"
    }
}