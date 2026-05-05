package com.androidforge.streakhappits.core.di

import android.content.Context
import com.androidforge.streakhappits.core.ads.AdMobManager
import com.androidforge.streakhappits.core.ads.GoogleAdMobManager
import com.androidforge.streakhappits.core.notifications.AndroidNotificationScheduler
import com.androidforge.streakhappits.core.notifications.NotificationScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideNotificationScheduler(@ApplicationContext context: Context): NotificationScheduler {
        return AndroidNotificationScheduler(context)
    }

    @Provides
    @Singleton
    fun provideAdMobManager(@ApplicationContext context: Context): AdMobManager {
        return GoogleAdMobManager(context)
    }
}