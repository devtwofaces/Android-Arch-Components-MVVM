package com.twofaces.androidarchcomponents_mvvm.di

import com.twofaces.androidarchcomponents_mvvm.adapters.NotesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    @Singleton
    @Provides
    fun providesNotesAdapter() = NotesAdapter()



}