package com.twofaces.androidarchcomponents_mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.databinding.ActivityAddNoteBinding
import com.twofaces.androidarchcomponents_mvvm.viewmodels.AddNoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {

    private lateinit var addNoteBinding: ActivityAddNoteBinding
    private lateinit var addNoteViewModel: AddNoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNoteBinding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(addNoteBinding.root)
//        setContentView(R.layout.activity_add_note)

        addNoteViewModel = ViewModelProvider(this)[AddNoteViewModel::class.java]


        addNoteBinding.addNoteActivityNumberPickerPriority.minValue = 1
        addNoteBinding.addNoteActivityNumberPickerPriority.maxValue = 10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"



    }



    private fun saveNote(){
        val title: String = addNoteBinding.addNoteActivityTitle.text.toString()
        val desc: String = addNoteBinding.addNoteActivityDescription.text.toString()
        val priority: Int = addNoteBinding.addNoteActivityNumberPickerPriority.value

        if(title.trim().isEmpty() || desc.trim().isEmpty()){
            Toast.makeText(this, "Please insert a title and description.", Toast.LENGTH_SHORT).show()
            return
        }
        addNoteViewModel.insert(Note(0, title, desc, priority))

        // Go to NoteActivity
        startActivity(Intent(this, NoteActivity::class.java))
        finish()

    }




    // Overriding Menu Options

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
//        return super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save_note -> {
                saveNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}