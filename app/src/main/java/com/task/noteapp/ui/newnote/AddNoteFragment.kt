package com.task.noteapp.ui.newnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentAddNoteBinding
import com.task.noteapp.model.NoteDataItem
import com.task.noteapp.util.AppUtil
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

            val note = NoteDataItem(viewModel.title.value,
                viewModel.description.value, AppUtil.getSystemTimeDate(),null,null)
            viewModel.validateAndAddNote(note)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigateBackToList.observe(viewLifecycleOwner, {
            if (it) {
                Navigation.findNavController(view).navigate(R.id.action_addNoteFragment_to_noteListFragment)
            }
        })

        viewModel.errorMsg.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

}