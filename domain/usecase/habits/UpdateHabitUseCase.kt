package com.androidforge.streakhappits.domain.usecase.habits

import com.androidforge.streakhappits.domain.model.Habit
import com.androidforge.streakhappits.domain.model.HabitFrequencyType
import com.androidforge.streakhappits.domain.repository.HabitRepository
import javax.inject.Inject

class UpdateHabitUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) {
        if (habit.name.isBlank()) {
            throw IllegalArgumentException("Habit name cannot be empty.")
        }
        if (habit.frequencyType == HabitFrequencyType.SPECIFIC_DAYS && habit.frequencyValue.isEmpty()) {
            throw IllegalArgumentException("Specific days must be selected for this frequency type.")
        }
        repository.updateHabit(habit)
    }
}