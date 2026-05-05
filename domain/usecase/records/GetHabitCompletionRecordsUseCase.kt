package com.androidforge.streakhappits.domain.usecase.records

import com.androidforge.streakhappits.domain.model.CompletionRecord
import com.androidforge.streakhappits.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHabitCompletionRecordsUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    operator fun invoke(habitId: Long): Flow<List<CompletionRecord>> {
        if (habitId <= 0) {
            throw IllegalArgumentException("Habit ID must be positive.")
        }
        return repository.getCompletionRecordsForHabit(habitId)
    }
}