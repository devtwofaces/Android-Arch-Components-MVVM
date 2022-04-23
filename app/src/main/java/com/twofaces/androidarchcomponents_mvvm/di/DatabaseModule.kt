package com.twofaces.androidarchcomponents_mvvm.di

import android.content.Context
import androidx.room.Room
import com.twofaces.androidarchcomponents_mvvm.data.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNoteDatabaseInstance(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, NoteDatabase::class.java,"note_database").build()

    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

}