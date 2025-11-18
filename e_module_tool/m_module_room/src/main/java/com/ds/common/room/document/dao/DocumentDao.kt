package com.ds.common.room.document.dao

import androidx.annotation.Keep
import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ds.common.room.document.data.HistoryDocument
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
interface DocumentDao {
    @Delete
    fun delete(documentItem: HistoryDocument)
    @Insert
    fun insert(documentItem: HistoryDocument)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert2(documentItem: HistoryDocument)
    @Query("SELECT * FROM HistoryDocument")
    fun getAllUsers(): LiveData<List<HistoryDocument>>

    @Update
    fun update(item: HistoryDocument)

    @Query("SELECT * from HistoryDocument WHERE id = :id")
    fun getItem(id: Int): Flow<HistoryDocument>

    @Query("SELECT * from HistoryDocument ORDER BY id ASC")
    fun getItems(): List<HistoryDocument>

    @Query("SELECT * FROM HistoryDocument ORDER BY id COLLATE NOCASE DESC")
    fun getItems2(): PagingSource<Int, HistoryDocument>
}