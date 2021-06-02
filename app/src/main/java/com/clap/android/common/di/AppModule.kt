package com.clap.android.common.di

import android.app.Application
import android.content.Context
import androidx.work.WorkManager
import com.clap.android.common.data.local.db.AppDatabase
import com.clap.android.common.data.local.db.AppPrefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This module will provide coroutines to be ready to injected in any class
 * for example we can inject it into Repos to do BG operations instead of calling into
 * ViewModels scope, also the Broadcast receivers or FCM for notifications or new tokens update
 */

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideWorkManager(application: Application): WorkManager {
        return WorkManager.getInstance(application)
    }

    @Singleton
    @Provides
    fun provideAppPrefs(@ApplicationContext context: Context) = AppPrefs(context)


}