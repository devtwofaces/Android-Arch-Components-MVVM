package com.twofaces.androidarchcomponents_mvvm.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.databinding.NoteItemBinding



class NotesAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private var notes: List<Note> = ArrayList<Note>()


    inner class ViewHolder(binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        var textViewTitle: TextView = binding.textViewTitle
        var textViewDescription: TextView = binding.textViewDescription
        var textViewPriority: TextView = binding.textViewPriority

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            if(adapterPosition != RecyclerView.NO_POSITION){
                listener.onItemClick(notes[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = NoteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote: Note = notes[position]
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textViewPriority.text = currentNote.priority.toString()

    }

    override fun getItemCount(): Int = notes.size

    fun setLatestNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(pos: Int): Note {
        return notes[pos]
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }


}