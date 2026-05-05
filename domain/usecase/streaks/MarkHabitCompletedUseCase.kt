package com.androidforge.streakhappits.domain.usecase.streaks

import com.androidforge.streakhappits.domain.model.CompletionRecord
import com.androidforge.streakhappits.domain.repository.HabitRepository
import java.time.LocalDate
import javax.inject.Inject

class MarkHabitCompletedUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habitId: Long, date: LocalDate = LocalDate.now(), completed: Boolean) {
        if (habitId <= 0) {
            throw IllegalArgumentException("Habit ID must be positive.")
        }

        if (completed) {
            val record = CompletionRecord(habitId = habitId, completionDate = date)
            repository.addCompletionRecord(record)
        } else {
            repository.deleteCompletionRecord(habitId, date)
        }
    }
}