package com.androidforge.streakhappits.domain.util

import com.androidforge.streakhappits.domain.model.CompletionRecord
import com.androidforge.streakhappits.domain.model.Habit
import com.androidforge.streakhappits.domain.model.HabitFrequencyType
import com.androidforge.streakhappits.domain.model.Streak
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset

object StreakCalculator {

    fun calculateStreaks(
        habit: Habit,
        completionRecords: List<CompletionRecord>,
        today: LocalDate
    ): Streak {
        if (!habit.isActive) {
            return Streak(currentStreak = 0, longestStreak = 0)
        }

        val completedDates = completionRecords.map { it.completionDate }.toSet()
        val habitCreationDate = habit.creationDate.atZone(ZoneOffset.UTC).toLocalDate()

        var currentStreak = 0
        var longestStreak = 0

        // Calculate current streak
        var checkDateForCurrent = today
        // If today is due and not completed, the current streak effectively ended yesterday.
        // We calculate the streak up to yesterday's date.
        if (isHabitDueOnDay(habit, today.dayOfWeek) && !completedDates.contains(today)) {
            checkDateForCurrent = today.minusDays(1)
        }

        while (!checkDateForCurrent.isBefore(habitCreationDate)) {
            if (isHabitDueOnDay(habit, checkDateForCurrent.dayOfWeek)) {
                if (completedDates.contains(checkDateForCurrent)) {
                    currentStreak++
                } else {
                    break // Streak broken
                }
            }
            checkDateForCurrent = checkDateForCurrent.minusDays(1)
        }

        // Calculate longest streak
        var tempLongestStreakCandidate = 0
        var checkDateForLongest = habitCreationDate

        while (!checkDateForLongest.isAfter(today)) {
            if (isHabitDueOnDay(habit, checkDateForLongest.dayOfWeek)) {
                if (completedDates.contains(checkDateForLongest)) {
                    tempLongestStreakCandidate++
                } else {
                    longestStreak = maxOf(longestStreak, tempLongestStreakCandidate)
                    tempLongestStreakCandidate = 0
                }
            }
            checkDateForLongest = checkDateForLongest.plusDays(1)
        }
        longestStreak = maxOf(longestStreak, tempLongestStreakCandidate) // Account for streak ending today

        return Streak(currentStreak = currentStreak, longestStreak = longestStreak)
    }

    // Helper function to determine if a habit is due on a specific day of the week
    fun isHabitDueOnDay(habit: Habit, day: DayOfWeek): Boolean {
        return when (habit.frequencyType) {
            HabitFrequencyType.DAILY -> true
            HabitFrequencyType.SPECIFIC_DAYS -> habit.frequencyValue.contains(day)
        }
    }

    // Helper to check if a habit was completed on a specific day
    fun isHabitCompletedOnDate(habitId: Long, date: LocalDate, completionRecords: List<CompletionRecord>): Boolean {
        return completionRecords.any { it.habitId == habitId && it.completionDate == date }
    }
}