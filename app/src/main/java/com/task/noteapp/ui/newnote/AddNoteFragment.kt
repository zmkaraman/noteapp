package com.task.noteapp.ui.newnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentAddNoteBinding
import com.task.noteapp.ui.notes.NoteDataItem
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddNoteFragment : Fragment() {

    lateinit var binding : FragmentAddNoteBinding
    val viewModel: AddNoteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_add_note, container, false
            )


        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.buttonAddNote.setOnClickListener {

            val note = NoteDataItem("Title","Description", null,null,null)
            viewModel.validateAndAddNote(note)
            //TODO Success message
            // Navigation.findNavController(it).navigate(R.id.action_addNoteFragment_to_noteListFragment)
        }

        return binding.root
    }


}