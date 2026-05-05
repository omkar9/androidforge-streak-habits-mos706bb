package com.androidforge.streakhappits.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidforge.streakhappits.data.local.model.CompletionRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletionRecordDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletionRecord(record: CompletionRecordEntity)

    @Query("DELETE FROM completion_records WHERE habitId = :habitId AND completionDateMillis = :completionDateMillis")
    suspend fun deleteCompletionRecord(habitId: Long, completionDateMillis: Long)

    @Query("SELECT * FROM completion_records WHERE habitId = :habitId ORDER BY completionDateMillis DESC")
    fun getCompletionRecordsForHabit(habitId: Long): Flow<List<CompletionRecordEntity>>

    @Query("SELECT COUNT(*) FROM completion_records WHERE habitId = :habitId AND completionDateMillis = :completionDateMillis")
    suspend fun isHabitCompletedOnDate(habitId: Long, completionDateMillis: Long): Int

    @Query("DELETE FROM completion_records WHERE habitId = :habitId")
    suspend fun deleteAllCompletionRecordsForHabit(habitId: Long)
}