package com.twofaces.androidarchcomponents_mvvm.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.twofaces.androidarchcomponents_mvvm.data.NoteRepository
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    fun insert(note: Note) = viewModelScope.launch {
        noteRepository.insert(note)
    }

    fun update(note: Note) = viewModelScope.launch {
        noteRepository.update(note)
    }

    fun delete(note: Note) = viewModelScope.launch {
        noteRepository.delete(note)
    }

    fun deleteAllNotes(note: Note) = viewModelScope.launch {
        noteRepository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> = noteRepository.getAllNotes().asLiveData()

}