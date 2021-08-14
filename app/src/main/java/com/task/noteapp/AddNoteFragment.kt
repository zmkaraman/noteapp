package com.task.noteapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.task.noteapp.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {

    lateinit var binding : FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_add_note, container, false
            )

        binding.buttonAddNote.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_addNoteFragment_to_noteListFragment)
        }

        return binding.root
    }


}