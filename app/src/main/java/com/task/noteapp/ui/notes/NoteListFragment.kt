package com.task.noteapp.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.noteapp.R
import com.task.noteapp.databinding.FragmentNoteListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteListFragment : Fragment() {

    lateinit var binding : FragmentNoteListBinding
    private val viewModel: NotesListViewModel by viewModel()
    private var adapter: NoteDataListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_note_list, container, false
            )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.addNoteFAB.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_noteListFragment_to_addNoteFragment)
        }

        //Add binding values
        adapter = NoteDataListAdapter(NoteDataListAdapter.NoteDataListener { note ->
            view?.let {
                Navigation.findNavController(it).navigate(NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(note))
            }
        })

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.notesRecyclerView.adapter = adapter

        viewModel.getNotes()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            it?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                binding.noDataTextView.visibility = View.VISIBLE
            }
        })

        viewModel.notesList.observe(viewLifecycleOwner, { noteData ->
            noteData?.apply {
                adapter?.submitList(this)
                if (noteData.isEmpty()) {
                    binding.noDataTextView.visibility = View.VISIBLE
                } else {
                    binding.noDataTextView.visibility = View.GONE
                }
            }
        })
    }

}