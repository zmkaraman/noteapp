package com.task.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.task.noteapp.databinding.FragmentNoteListBinding

class NoteListFragment : Fragment() {


    lateinit var binding : FragmentNoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_note_list, container, false
            )

        binding.addNoteFAB.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }

        return binding.root
    }


}