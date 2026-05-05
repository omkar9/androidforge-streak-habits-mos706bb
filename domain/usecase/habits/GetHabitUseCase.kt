package com.androidforge.streakhappits.domain.usecase.habits

import com.androidforge.streakhappits.domain.model.Habit
import com.androidforge.streakhappits.domain.repository.HabitRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHabitUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    operator fun invoke(habitId: Long): Flow<Habit?> {
        if (habitId <= 0) {
            throw IllegalArgumentException("Habit ID must be positive.")
        }
        return repository.getHabitById(habitId)
    }
}