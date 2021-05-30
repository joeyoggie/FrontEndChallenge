package com.clap.android.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModule {
    /*@Binds
    abstract fun bindVirtualAppointmentsRepository(virtualAppointmentsRepository: VirtualAppointmentsRepository): IVirtualAppointmentsRepository*/
}