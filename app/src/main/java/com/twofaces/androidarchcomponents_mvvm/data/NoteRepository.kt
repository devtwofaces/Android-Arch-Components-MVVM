package com.twofaces.androidarchcomponents_mvvm.data

import androidx.annotation.WorkerThread
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NoteRepository @Inject constructor(
        private val noteDao: NoteDao
    )
{
//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
    suspend fun insert(note: Note){
        noteDao.insertNote(note)
    }
//    @WorkerThread
    suspend fun update(note: Note){
        noteDao.updateNote(note)
    }
//    @WorkerThread
    suspend fun delete(note: Note){
        noteDao.deleteNote(note)
    }
//    @WorkerThread
    suspend fun deleteAllNotes(){
        noteDao.deleteALlNotes()
    }
//    @WorkerThread
    fun getAllNotes(): Flow<List<Note>>{
        return noteDao.getAllNotes()
    }

}