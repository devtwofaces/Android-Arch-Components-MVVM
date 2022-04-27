package com.twofaces.androidarchcomponents_mvvm.adapters


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.databinding.NoteItemBinding



class NotesAdapter(private val listener: OnItemClickListener): ListAdapter<Note, NotesAdapter.ViewHolder>(DiffCallback()) {

//    private var notes: List<Note> = ArrayList<Note>()

    //  DiffCallback method
        private class DiffCallback: DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.title == newItem.title
                        && oldItem.description == newItem.description
                        && oldItem.priority == newItem.priority
            }
        }

    //  Defining ViewHolder class for the RecyclerView
        inner class ViewHolder(binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
            var textViewTitle: TextView = binding.textViewTitle
            var textViewDescription: TextView = binding.textViewDescription
            var textViewPriority: TextView = binding.textViewPriority

            init{
                // setOnClickListener for the current ViewHolder item
                itemView.setOnClickListener(this)
            }

            // Overriding the onClick method defined in the Interface.
                // If any class implements an Interface, it becomes the responsibility of the implementing class to
                // override all the methods defined in the Interface
            override fun onClick(p0: View?) {
                // Check if the clicked RecyclerView item exists
                if(adapterPosition != RecyclerView.NO_POSITION){
                    // Call the overridden onItemClick(note: Note) method in NoteActivity.kt
                        listener.onItemClick(getItem(adapterPosition))
                }
            }
        }

    //  Overriding method onCreateViewHolder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //  Inflate the layout of note_item.xml via View Binding
            val view = NoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return ViewHolder(view)
        }

    //  Overriding method onBindViewHolder
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //  Setting the values for view item in the RecyclerView
            val currentNote: Note = getItem(position)
            holder.textViewTitle.text = currentNote.title
            holder.textViewDescription.text = currentNote.description
            holder.textViewPriority.text = currentNote.priority.toString()
        }

//    override fun getItemCount(): Int = notes.size
//
//    fun setLatestNotes(notes: List<Note>) {
//        this.notes = notes
//        notifyDataSetChanged()
//    }

    // Returns the Note object at given position "pos"
    fun getNoteAt(pos: Int): Note {
        return getItem(pos)
    }

    // Custom OnItemClickListener Interface
    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }


}