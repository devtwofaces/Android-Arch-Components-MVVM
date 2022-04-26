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
        noteRepositoryProvider: Provider<NoteRepository>,
        coroutineScopeProvider: Provider<CoroutineScope>
    ) = Room.databaseBuilder(context, NoteDatabase::class.java,"note_database")
        .addCallback(
            object: RoomDatabase.Callback(){
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    coroutineScopeProvider.get().launch {
                        noteRepositoryProvider.get().deleteAllNotes()
                        noteRepositoryProvider.get().insert(Note(0, "Title 1", "Description 1", 1))
                        noteRepositoryProvider.get().insert(Note(0, "Title 2", "Description 2", 2))
                        noteRepositoryProvider.get().insert(Note(0, "Title 3", "Description 3", 3))
                        noteRepositoryProvider.get().insert(Note(0, "Title 4", "Description 4", 4))
                    }
                }
            }
        )
        .build()

    @Singleton
    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        // Run this code when providing an instance of CoroutineScope
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

}