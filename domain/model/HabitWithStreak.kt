package com.androidforge.streakhappits.domain.model

data class HabitWithStreak(
    val habit: Habit,
    val currentStreak: Int,
    val longestStreak: Int,
    val isCompletedToday: Boolean
)