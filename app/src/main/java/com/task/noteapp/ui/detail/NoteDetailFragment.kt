package com.task.noteapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentNoteDetailBinding
import com.task.noteapp.ui.notes.NoteDataItem
import com.task.noteapp.util.AppUtil
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteDetailFragment : Fragment(){

    lateinit var binding : FragmentNoteDetailBinding
    val viewModel: NoteDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_note_detail, container, false
            )

        val note = arguments?.let {
            NoteDetailFragmentArgs.fromBundle(it).note
        }

        binding.lifecycleOwner = this
        binding.noteDataItem = note

        binding.buttonDeleteNote.setOnClickListener {
            note?.let {
                viewModel.deleteNote(it.id)
            }
        }

        binding.buttonUpdateNote.setOnClickListener {
            note?.let {
                val note = NoteDataItem(binding.noteDataItem?.title,
                    binding.noteDataItem?.description, note.createDate,
                    AppUtil.getSystemTimeDate(), note.imageUrl, note.id)
                viewModel.validateAndUpdateNote(note)
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateBackToList.observe(viewLifecycleOwner, {
            if (it) {
                Navigation.findNavController(view).navigate(R.id.action_noteDetailFragment_to_noteListFragment)
            }
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

}