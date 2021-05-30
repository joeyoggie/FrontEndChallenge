package com.clap.android.common.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * This Module will contains all related network dependencies
 * which need to be live till the app is living
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {}