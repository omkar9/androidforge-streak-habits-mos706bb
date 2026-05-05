package com.androidforge.streakhappits.domain.usecase.streaks

import com.androidforge.streakhappits.domain.model.Streak
import com.androidforge.streakhappits.domain.repository.HabitRepository
import com.androidforge.streakhappits.domain.util.StreakCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetHabitStreakUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    operator fun invoke(habitId: Long, today: LocalDate = LocalDate.now()): Flow<Streak> {
        if (habitId <= 0) {
            throw IllegalArgumentException("Habit ID must be positive.")
        }
        return combine(
            repository.getHabitById(habitId).filterNotNull(),
            repository.getCompletionRecordsForHabit(habitId)
        ) { habit, records ->
            StreakCalculator.calculateStreaks(habit, records, today)
        }
    }
}