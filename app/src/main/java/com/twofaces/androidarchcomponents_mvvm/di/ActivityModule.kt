package com.twofaces.androidarchcomponents_mvvm.di

import com.twofaces.androidarchcomponents_mvvm.NoteApplication
import com.twofaces.androidarchcomponents_mvvm.adapters.NotesAdapter
import com.twofaces.androidarchcomponents_mvvm.data.NoteRepository
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.NoteDao
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