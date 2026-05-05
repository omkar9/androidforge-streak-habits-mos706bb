package com.androidforge.streakhappits.data.repository

import com.androidforge.streakhappits.data.local.database.CompletionRecordDao
import com.androidforge.streakhappits.data.local.database.HabitDao
import com.androidforge.streakhappits.data.mapper.toDomain
import com.androidforge.streakhappits.data.mapper.toEntity
import com.androidforge.streakhappits.domain.model.CompletionRecord
import com.androidforge.streakhappits.domain.model.Habit
import com.androidforge.streakhappits.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.DayOfWeek
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HabitRepositoryImpl @Inject constructor(
    private val habitDao: HabitDao,
    private val completionRecordDao: CompletionRecordDao
) : HabitRepository {

    override suspend fun addHabit(habit: Habit): Long {
        return habitDao.insertHabit(habit.toEntity())
    }

    override suspend fun updateHabit(habit: Habit) {
        habitDao.updateHabit(habit.toEntity())
    }

    override suspend fun deleteHabit(habitId: Long) {
        // Also delete associated completion records
        completionRecordDao.deleteAllCompletionRecordsForHabit(habitId)
        habitDao.deleteHabitById(habitId)
    }

    override fun getHabitById(habitId: Long): Flow<Habit?> {
        return habitDao.getHabitById(habitId).map { it?.toDomain() }
    }

    override fun getAllActiveHabits(): Flow<List<Habit>> {
        return habitDao.getAllActiveHabits().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getActiveHabitsForDay(dayOfWeek: DayOfWeek): Flow<List<Habit>> {
        return habitDao.getActiveHabitsForDay(dayOfWeek.name).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addCompletionRecord(completionRecord: CompletionRecord) {
        completionRecordDao.insertCompletionRecord(completionRecord.toEntity())
    }

    override suspend fun deleteCompletionRecord(habitId: Long, date: LocalDate) {
        completionRecordDao.deleteCompletionRecord(habitId, date.toEpochDay())
    }

    override fun getCompletionRecordsForHabit(habitId: Long): Flow<List<CompletionRecord>> {
        return completionRecordDao.getCompletionRecordsForHabit(habitId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun isHabitCompletedOnDate(habitId: Long, date: LocalDate): Boolean {
        return completionRecordDao.isHabitCompletedOnDate(habitId, date.toEpochDay()) > 0
    }

    override suspend fun deleteAllCompletionRecordsForHabit(habitId: Long) {
        completionRecordDao.deleteAllCompletionRecordsForHabit(habitId)
    }
}