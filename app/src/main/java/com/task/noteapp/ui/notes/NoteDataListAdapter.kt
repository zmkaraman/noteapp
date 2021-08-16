package com.task.noteapp.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.noteapp.databinding.ItemNoteBinding
import com.task.noteapp.model.NoteDataItem

class NoteDataListAdapter(private val clickListener: NoteDataListener) :
    ListAdapter<NoteDataItem, NoteDataListAdapter.NoteDataViewHolder>(NoteDataDiffCallback) {

    class NoteDataListener(val clickListener: (note: NoteDataItem) -> Unit) {
        fun onClick(note: NoteDataItem) = clickListener(note)
    }

    class NoteDataViewHolder(private var binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: NoteDataListener, note: NoteDataItem) {
            binding.note = note
            binding.clickListener = listener
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): NoteDataViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
                return NoteDataViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteDataViewHolder {
        return NoteDataViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: NoteDataViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }
}

class NoteDataDiffCallback {
    companion object DiffCallback : DiffUtil.ItemCallback<NoteDataItem>() {
        override fun areItemsTheSame(oldItem: NoteDataItem, newItem: NoteDataItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NoteDataItem, newItem: NoteDataItem): Boolean {
            return newItem == oldItem
        }
    }
}


