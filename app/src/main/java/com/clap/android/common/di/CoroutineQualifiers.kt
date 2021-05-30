package com.clap.android.common.di

import javax.inject.Qualifier

/**
 * These annotations qualifiers like @Inject , we can add it to define the
 * Injectable dispatcher
 */

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApplicationScope