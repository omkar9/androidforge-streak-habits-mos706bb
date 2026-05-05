package com.androidforge.streakhappits.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class HabitEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String?,
    val creationDateMillis: Long, // Instant as millis
    val frequencyType: String, // Stored as String (enum name)
    val frequencyValue: String, // Stored as comma-separated string of DayOfWeek enum names
    val reminderTimeString: String?, // LocalTime as String
    val isActive: Boolean
)