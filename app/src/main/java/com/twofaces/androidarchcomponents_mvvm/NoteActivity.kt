package com.twofaces.androidarchcomponents_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.twofaces.androidarchcomponents_mvvm.adapters.NotesAdapter
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.databinding.ActivityNoteBinding
import com.twofaces.androidarchcomponents_mvvm.utilities.*
import com.twofaces.androidarchcomponents_mvvm.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NoteActivity : AppCompatActivity(), NotesAdapter.OnItemClickListener {


    // Initialization of View Binding, View Model and Adapter objects
    private lateinit var noteBinding: ActivityNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    var adapter = NotesAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_note)

        // Initializing the view-binding object for activity_note.xml
            noteBinding = ActivityNoteBinding.inflate(layoutInflater)
        // Setting the content view as the root element/view in activity_note.xml (CoordinatorLayout)
            setContentView(noteBinding.root)

        // Initializing the View Model for NoteActivity class -> NoteViewModel.kt
            noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        // Setting up the RecyclerView
            val recyclerView: RecyclerView = noteBinding.noteActivityRecyclerViewNotes
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = adapter

        // Observing all the notes in the RecyclerView
            noteViewModel.getAllNotes().observe(this, Observer<List<Note>> {
                // adapter.setLatestNotes(it)
                adapter.submitList(it)
            })

       // Setting up ItemTouchHelper for swiping feature of RecyclerView Item
       // & attaching it to the Recycler View (noteActivityRecyclerViewNotes)
           ItemTouchHelper(
                object : ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT
                    // or ItemTouchHelper.RIGHT
                ) {
                    // For Drag & Drop feature
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        TODO("Not yet implemented")
                    }
                    // For swipe feature
                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        // Delete the Note in SQLite database using Room for the swiped list item in the RecyclerView
                        noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                        Toast.makeText(this@NoteActivity, "Note deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            ).attachToRecyclerView(noteBinding.noteActivityRecyclerViewNotes)

    }


    // ADDING A NEW NOTE
        fun addNote(view: View) {
            // Go to AddEditNoteActivity
            startActivity(Intent(this, AddEditNoteActivity::class.java))
            finish()
        }

    // UPDATING AN EXISTING NOTE
        override fun onItemClick(note: Note) {
            Log.d("CUSTOM_LOGS: ", "inside onItemClick()")
            val intent = Intent(this@NoteActivity, AddEditNoteActivity::class.java)
            intent.putExtra(EXTRA_ID, note.id)
            intent.putExtra(EXTRA_TITLE, note.title)
            intent.putExtra(EXTRA_DESC, note.description)
            intent.putExtra(EXTRA_PRIORITY, note.priority)
            intent.putExtra("REQUEST_METHOD", "EDIT_NOTE_REQUEST")
            startActivity(intent)
            finish()
        }


    // OVERRIDING CUSTOM MENU OPTIONS
        // onCreateOptionsMenu
        override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.main_note_menu, menu)
            return true
        }
        // onOptionsItemSelected
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.noteActivity_delete_all_notes -> {
                    noteViewModel.deleteAllNotes()
                    Toast.makeText(this@NoteActivity, "All notes deleted", Toast.LENGTH_SHORT).show()
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
        }

}