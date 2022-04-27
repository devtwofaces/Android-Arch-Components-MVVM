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
import javax.inject.Inject


@AndroidEntryPoint
class NoteActivity : AppCompatActivity(), NotesAdapter.OnItemClickListener {

    private lateinit var noteBinding: ActivityNoteBinding
    private lateinit var noteViewModel: NoteViewModel

    var adapter = NotesAdapter(this)


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


        val x = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
//                        or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                    Toast.makeText(this@NoteActivity, "Note deleted", Toast.LENGTH_SHORT).show()
                }
            }
        ).attachToRecyclerView(noteBinding.noteActivityRecyclerViewNotes)

    }

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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_note_menu, menu)
        return true
    }

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


    fun addNote(view: View) {
        // Go to AddEditNoteActivity
        startActivity(Intent(this, AddEditNoteActivity::class.java))
        finish()
    }


}