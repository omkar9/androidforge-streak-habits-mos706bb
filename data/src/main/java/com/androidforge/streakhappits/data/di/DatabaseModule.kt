package com.androidforge.streakhappits.data.di

import android.content.Context
import androidx.room.Room
import com.androidforge.streakhappits.data.local.database.CompletionRecordDao
import com.androidforge.streakhappits.data.local.database.HabitDao
import com.androidforge.streakhappits.data.local.database.StreakHabitsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): StreakHabitsDatabase {
        return Room.databaseBuilder(
            context,
            StreakHabitsDatabase::class.java,
            StreakHabitsDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideHabitDao(database: StreakHabitsDatabase): HabitDao {
        return database.habitDao()
    }

    @Provides
    @Singleton
    fun provideCompletionRecordDao(database: StreakHabitsDatabase): CompletionRecordDao {
        return database.completionRecordDao()
    }
}