package com.twofaces.androidarchcomponents_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.databinding.ActivityMainBinding
import com.twofaces.androidarchcomponents_mvvm.viewmodels.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint



class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }
}