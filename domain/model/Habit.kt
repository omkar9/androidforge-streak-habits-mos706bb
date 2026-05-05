package com.androidforge.streakhappits.domain.model

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalTime

data class Habit(
    val id: Long = 0,
    val name: String,
    val description: String?,
    val creationDate: Instant,
    val frequencyType: HabitFrequencyType,
    val frequencyValue: Set<DayOfWeek>, // Using Set for uniqueness and order independence
    val reminderTime: LocalTime?,
    val isActive: Boolean
)