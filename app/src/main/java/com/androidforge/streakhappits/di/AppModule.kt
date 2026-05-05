package com.androidforge.streakhappits.di

import android.content.Context
import com.androidforge.streakhappits.presentation.onboarding.OnboardingPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOnboardingPrefManager(@ApplicationContext context: Context): OnboardingPrefManager {
        return OnboardingPrefManager(context)
    }
}