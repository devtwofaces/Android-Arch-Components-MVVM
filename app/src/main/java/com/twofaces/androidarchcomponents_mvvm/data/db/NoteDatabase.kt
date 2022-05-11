package com.twofaces.androidarchcomponents_mvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.twofaces.androidarchcomponents_mvvm.data.NoteRepository
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.NoteDao
import com.twofaces.androidarchcomponents_mvvm.di.TaskActivityScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    class RoomCallBack @Inject constructor(
        @TaskActivityScope private val coroutineScope: CoroutineScope,
        private val noteRepositoryProvider: Provider<NoteRepository>
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            coroutineScope.launch {
                noteRepositoryProvider.get().deleteAllNotes()
                noteRepositoryProvider.get().insert(Note(0, "Title 1", "Description 1", 1))
                noteRepositoryProvider.get().insert(Note(0, "Title 2", "Description 2", 2))
                noteRepositoryProvider.get().insert(Note(0, "Title 3", "Description 3", 3))
                noteRepositoryProvider.get().insert(Note(0, "Title 4", "Description 4", 4))
            }

        }
    }


}



