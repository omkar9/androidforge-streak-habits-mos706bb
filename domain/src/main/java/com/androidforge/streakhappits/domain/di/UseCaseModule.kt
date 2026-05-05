package com.androidforge.streakhappits.domain.di

import com.androidforge.streakhappits.domain.repository.HabitRepository
import com.androidforge.streakhappits.domain.usecase.habits.AddHabitUseCase
import com.androidforge.streakhappits.domain.usecase.habits.DeleteHabitUseCase
import com.androidforge.streakhappits.domain.usecase.habits.GetAllHabitsUseCase
import com.androidforge.streakhappits.domain.usecase.habits.GetHabitUseCase
import com.androidforge.streakhappits.domain.usecase.habits.UpdateHabitUseCase
import com.androidforge.streakhappits.domain.usecase.records.GetHabitCompletionRecordsUseCase
import com.androidforge.streakhappits.domain.usecase.streaks.GetHabitStreakUseCase
import com.androidforge.streakhappits.domain.usecase.streaks.MarkHabitCompletedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAddHabitUseCase(repository: HabitRepository): AddHabitUseCase {
        return AddHabitUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateHabitUseCase(repository: HabitRepository): UpdateHabitUseCase {
        return UpdateHabitUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteHabitUseCase(repository: HabitRepository): DeleteHabitUseCase {
        return DeleteHabitUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetHabitUseCase(repository: HabitRepository): GetHabitUseCase {
        return GetHabitUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAllHabitsUseCase(repository: HabitRepository): GetAllHabitsUseCase {
        return GetAllHabitsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideMarkHabitCompletedUseCase(repository: HabitRepository): MarkHabitCompletedUseCase {
        return MarkHabitCompletedUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetHabitStreakUseCase(repository: HabitRepository): GetHabitStreakUseCase {
        return GetHabitStreakUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetHabitCompletionRecordsUseCase(repository: HabitRepository): GetHabitCompletionRecordsUseCase {
        return GetHabitCompletionRecordsUseCase(repository)
    }
}