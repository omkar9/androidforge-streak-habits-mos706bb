package com.androidforge.streakhappits.domain.usecase.habits

import com.androidforge.streakhappits.domain.model.HabitWithStreak
import com.androidforge.streakhappits.domain.repository.HabitRepository
import com.androidforge.streakhappits.domain.util.StreakCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class GetAllHabitsUseCase @Inject constructor(
    private val repository: HabitRepository
) {
    operator fun invoke(today: LocalDate = LocalDate.now()): Flow<List<HabitWithStreak>> {
        return repository.getAllActiveHabits().flatMapLatest { habits ->
            if (habits.isEmpty()) {
                flowOf(emptyList())
            } else {
                // For each habit, get its completion records and combine to calculate streak
                val habitFlows = habits.map { habit ->
                    repository.getCompletionRecordsForHabit(habit.id).map { records ->
                        val streak = StreakCalculator.calculateStreaks(habit, records, today)
                        val isCompletedToday = StreakCalculator.isHabitCompletedOnDate(habit.id, today, records)
                        HabitWithStreak(
                            habit = habit,
                            currentStreak = streak.currentStreak,
                            longestStreak = streak.longestStreak,
                            isCompletedToday = isCompletedToday
                        )
                    }
                }
                combine(habitFlows) { it.toList() }
            }
        }
    }
}