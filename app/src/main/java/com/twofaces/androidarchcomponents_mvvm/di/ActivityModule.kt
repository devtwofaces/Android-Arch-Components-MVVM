package com.twofaces.androidarchcomponents_mvvm.di

import com.twofaces.androidarchcomponents_mvvm.adapters.NotesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK
// THIS FILE IS NOT NEEDED FOR THIS APPLICATION TO WORK


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

