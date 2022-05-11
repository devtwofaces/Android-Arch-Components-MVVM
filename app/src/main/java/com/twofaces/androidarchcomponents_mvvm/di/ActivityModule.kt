package com.twofaces.androidarchcomponents_mvvm.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    @TaskActivityScope
    @Singleton
    @Provides
    fun providesCoroutineScope() = CoroutineScope(SupervisorJob() + Dispatchers.IO)

}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class TaskActivityScope

