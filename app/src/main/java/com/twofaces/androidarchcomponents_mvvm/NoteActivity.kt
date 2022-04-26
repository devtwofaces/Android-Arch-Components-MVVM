package com.twofaces.androidarchcomponents_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twofaces.androidarchcomponents_mvvm.adapters.NotesAdapter
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.databinding.ActivityMainBinding
import com.twofaces.androidarchcomponents_mvvm.databinding.ActivityNoteBinding
import com.twofaces.androidarchcomponents_mvvm.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class NoteActivity : AppCompatActivity() {

    private lateinit var noteBinding: ActivityNoteBinding

    @Inject
    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        noteBinding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(noteBinding.root)
//        setContentView(R.layout.activity_main)


        val viewModel: NoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        val recyclerView: RecyclerView = noteBinding.noteActivityRecyclerViewNotes
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        recyclerView.adapter = adapter

        viewModel.getAllNotes().observe(this, Observer<List<Note>> {
            Toast.makeText(this, "observer()", Toast.LENGTH_SHORT).show()
            adapter.setLatestNotes(it)
        })



    }
}