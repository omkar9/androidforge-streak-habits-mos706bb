package com.androidforge.streakhappits.domain.repository

import com.androidforge.streakhappits.domain.model.CompletionRecord
import com.androidforge.streakhappits.domain.model.Habit
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.DayOfWeek

interface HabitRepository {
    suspend fun addHabit(habit: Habit): Long
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habitId: Long)
    fun getHabitById(habitId: Long): Flow<Habit?>
    fun getAllActiveHabits(): Flow<List<Habit>>
    fun getActiveHabitsForDay(dayOfWeek: DayOfWeek): Flow<List<Habit>>

    suspend fun addCompletionRecord(completionRecord: CompletionRecord)
    suspend fun deleteCompletionRecord(habitId: Long, date: LocalDate)
    fun getCompletionRecordsForHabit(habitId: Long): Flow<List<CompletionRecord>>
    suspend fun isHabitCompletedOnDate(habitId: Long, date: LocalDate): Boolean
    suspend fun deleteAllCompletionRecordsForHabit(habitId: Long)
}