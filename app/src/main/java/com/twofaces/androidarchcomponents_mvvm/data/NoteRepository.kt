package com.twofaces.androidarchcomponents_mvvm.data

import androidx.lifecycle.LiveData
import com.twofaces.androidarchcomponents_mvvm.data.db.NoteDatabase
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDatabase: NoteDatabase,
    private val noteDao: NoteDao
) {

    private var allNotes: LiveData<List<Note>> = noteDao.getAllNotes()



}