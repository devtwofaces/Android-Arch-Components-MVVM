package com.twofaces.androidarchcomponents_mvvm.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.twofaces.androidarchcomponents_mvvm.data.NoteRepository
import com.twofaces.androidarchcomponents_mvvm.data.db.NoteDatabase
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.*
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideNoteDatabaseInstance(
        @ApplicationContext context: Context,
        callback: NoteDatabase.RoomCallBack
    ) = Room.databaseBuilder(context, NoteDatabase::class.java,"note_database")
        .addCallback(callback)
        .build()



    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

}