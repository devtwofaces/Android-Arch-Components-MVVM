package com.twofaces.androidarchcomponents_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
//        setContentView(R.layout.activity_main)


        val viewModel: NoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]


        viewModel.getAllNotes().observe(this, Observer<List<Note>> {
            Toast.makeText(this, "observer()", Toast.LENGTH_SHORT).show()
        })



    }
}