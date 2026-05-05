package com.androidforge.streakhappits.domain.usecase.habits

import com.androidforge.streakhappits.domain.repository.HabitRepository
import javax.inject.Inject

class DeleteHabitUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habitId: Long) {
        if (habitId <= 0) {
            throw IllegalArgumentException("Habit ID must be positive.")
        }
        repository.deleteHabit(habitId)
    }
}