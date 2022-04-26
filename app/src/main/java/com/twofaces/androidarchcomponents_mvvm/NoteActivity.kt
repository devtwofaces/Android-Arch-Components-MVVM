package com.twofaces.androidarchcomponents_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twofaces.androidarchcomponents_mvvm.adapters.NotesAdapter
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.databinding.ActivityNoteBinding
import com.twofaces.androidarchcomponents_mvvm.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private lateinit var noteBinding: ActivityNoteBinding
    private lateinit var noteViewModel: NoteViewModel

    @Inject
    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        noteBinding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(noteBinding.root)
//        setContentView(R.layout.activity_main)


        noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        val recyclerView: RecyclerView = noteBinding.noteActivityRecyclerViewNotes
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        recyclerView.adapter = adapter

        noteViewModel.getAllNotes().observe(this, Observer<List<Note>> {
//            Toast.makeText(this, "observer()", Toast.LENGTH_SHORT).show()
            adapter.setLatestNotes(it)
        })



    }


    fun addNote(view: View){
        // Go to AddNoteActivity
        startActivity(Intent(this, AddNoteActivity::class.java))
        finish()
    }



}