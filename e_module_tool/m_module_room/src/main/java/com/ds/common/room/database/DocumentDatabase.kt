package com.ds.common.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ds.common.ext.appContext
import com.ds.common.room.document.dao.DocumentDao
import com.ds.common.room.document.data.HistoryDocument
import com.ds.common.room.database.DocumentDatabase.Companion.DATA_BASE_VERSION

val database: DocumentDatabase by lazy {
    DocumentDatabase.getDatabase(appContext.applicationContext)
}

@Database(entities = [HistoryDocument::class], version = DATA_BASE_VERSION, exportSchema = false)
abstract class DocumentDatabase : RoomDatabase() {
    abstract fun documentDao(): DocumentDao

    companion object {
        @Volatile
        private var INSTANCE: DocumentDatabase? = null
        const val DATA_BASE_NAME = "document_database"
        const val DATA_BASE_VERSION = 3
        fun getDatabase(context: Context): DocumentDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, DocumentDatabase::class.java, DATA_BASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}