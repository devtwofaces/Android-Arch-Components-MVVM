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
import com.twofaces.androidarchcomponents_mvvm.databinding.ActivityAddEditNoteBinding
import com.twofaces.androidarchcomponents_mvvm.utilities.EXTRA_DESC
import com.twofaces.androidarchcomponents_mvvm.utilities.EXTRA_ID
import com.twofaces.androidarchcomponents_mvvm.utilities.EXTRA_PRIORITY
import com.twofaces.androidarchcomponents_mvvm.utilities.EXTRA_TITLE
import com.twofaces.androidarchcomponents_mvvm.viewmodels.AddEditNoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddEditNoteActivity : AppCompatActivity() {

    // Initialization of View Binding & View Model objects
    private lateinit var addNoteBinding: ActivityAddEditNoteBinding
    private lateinit var addEditNoteViewModel: AddEditNoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_edit_note)

        // Initializing the view-binding object for activity_add_edit_note.xml
            addNoteBinding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        // Setting the content view as the root element/view in activity_add_edit_note.xml (LinearLayout)
            setContentView(addNoteBinding.root)

        // Initializing the View Model for AddEditNoteActivity class -> AddEditNoteViewModel.kt
            addEditNoteViewModel = ViewModelProvider(this)[AddEditNoteViewModel::class.java]

        // Setting the MAX and MIN values for Number Picker view
            addNoteBinding.addNoteActivityNumberPickerPriority.minValue = 1
            addNoteBinding.addNoteActivityNumberPickerPriority.maxValue = 10

        // Custom 'X' icon as 'Close'
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        // Set title of the activity
            if(intent.hasExtra(EXTRA_ID)){
                title = "Edit Note"
                addNoteBinding.addNoteActivityTitle.setText(intent.getStringExtra(EXTRA_TITLE))
                addNoteBinding.addNoteActivityDescription.setText(intent.getStringExtra(EXTRA_DESC))
                addNoteBinding.addNoteActivityNumberPickerPriority.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
            }
            else {
                title = "Add Note"
            }
    }

    // Saving the Note(s) to SQLite DB using ROOM - ADD || EDIT
        private fun saveNote(){

            val title: String = addNoteBinding.addNoteActivityTitle.text.toString()
            val desc: String = addNoteBinding.addNoteActivityDescription.text.toString()
            val priority: Int = addNoteBinding.addNoteActivityNumberPickerPriority.value

            // Check if the Title & Description fields are empty
            if(title.trim().isEmpty() || desc.trim().isEmpty()){
                Toast.makeText(this, "Please insert a title and description.", Toast.LENGTH_SHORT).show()
                return
            }

            // Edit existing Note
                if(intent.getStringExtra("REQUEST_METHOD") == "EDIT_NOTE_REQUEST"){
                // Check if ID of the Note exists
                    if(intent.getIntExtra(EXTRA_ID, -1) == -1){
                        Toast.makeText(this, "Note cannot be updated.", Toast.LENGTH_SHORT).show()
                        return
                    }
                    // Run UPDATE query on the edited Note
                    addEditNoteViewModel.update(
                        Note(intent.getIntExtra(EXTRA_ID, -1), title, desc, priority)
                    )
                }
            // Add new Note
                else{
                    // Run INSERT query on the newly created Note
                    addEditNoteViewModel.insert(
                        Note(0, title, desc, priority)
                    )
                }

            // Go back to NoteActivity
            startActivity(Intent(this, NoteActivity::class.java))
            finish()

        }


    // OVERRIDING CUSTOM MENU OPTIONS
        // onCreateOptionsMenu
            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                val menuInflater: MenuInflater = menuInflater
                menuInflater.inflate(R.menu.add_note_menu, menu)
                return true
            }

        // onOptionsItemSelected
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