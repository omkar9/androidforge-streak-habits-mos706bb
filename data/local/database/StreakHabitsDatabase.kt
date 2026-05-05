package com.androidforge.streakhappits.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androidforge.streakhappits.data.local.model.CompletionRecordEntity
import com.androidforge.streakhappits.data.local.model.HabitEntity

@Database(
    entities = [HabitEntity::class, CompletionRecordEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class StreakHabitsDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
    abstract fun completionRecordDao(): CompletionRecordDao

    companion object {
        const val DATABASE_NAME = "streak_habits_db"
    }
}


// data/local/database/Converters.kt
package com.androidforge.streakhappits.data.local.database

import androidx.room.TypeConverter
import com.androidforge.streakhappits.domain.model.HabitFrequencyType
import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

class Converters {

    @TypeConverter
    fun fromInstantToMillis(instant: Instant?): Long? {
        return instant?.toEpochMilli()
    }

    @TypeConverter
    fun fromMillisToInstant(millis: Long?): Instant? {
        return millis?.let { Instant.ofEpochMilli(it) }
    }

    @TypeConverter
    fun fromLocalDateToEpochDay(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }

    @TypeConverter
    fun fromEpochDayToLocalDate(epochDay: Long?): LocalDate? {
        return epochDay?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun fromLocalTimeToString(time: LocalTime?): String? {
        return time?.toString()
    }

    @TypeConverter
    fun fromStringToLocalTime(timeString: String?): LocalTime? {
        return timeString?.let { LocalTime.parse(it) }
    }

    @TypeConverter
    fun fromHabitFrequencyTypeToString(type: HabitFrequencyType?): String? {
        return type?.name
    }

    @TypeConverter
    fun fromStringToHabitFrequencyType(typeString: String?): HabitFrequencyType? {
        return typeString?.let { HabitFrequencyType.valueOf(it) }
    }

    @TypeConverter
    fun fromDayOfWeekSetToString(dayOfWeeks: Set<DayOfWeek>?): String? {
        return dayOfWeeks?.joinToString(",") { it.name }
    }

    @TypeConverter
    fun fromStringToDayOfWeekSet(dayOfWeekString: String?): Set<DayOfWeek>? {
        return dayOfWeekString
            ?.split(",")
            ?.filter { it.isNotBlank() }
            ?.map { DayOfWeek.valueOf(it) }
            ?.toSet()
    }
}