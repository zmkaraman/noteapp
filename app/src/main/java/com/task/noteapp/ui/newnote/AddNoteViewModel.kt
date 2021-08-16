package com.task.noteapp.ui.newnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.NoteDataSource
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.model.NoteDataItem
import kotlinx.coroutines.launch

class AddNoteViewModel(
    app: Application,
    private val dataSource: NoteDataSource
) : AndroidViewModel(app) {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val navigateBackToList = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()

    fun validateAndAddNote(note: NoteDataItem) {
        if (validateEnteredData(note)) {
            addNote(note)
        }
    }



    /**
     * Save the note to the data source
     */
    private fun addNote(note: NoteDataItem) {
        viewModelScope.launch {
            dataSource.addNote(
                NoteDTO(
                    note.title,
                    note.description,
                    note.createDate,
                    note.updateDate,
                    note.imageUrl,
                    note.id
                )
            )
            navigateBackToList.value = true
        }
    }

    private fun validateEnteredData(note: NoteDataItem): Boolean {
        if (note.title.isNullOrEmpty() || note.description.isNullOrEmpty()) {
            navigateBackToList.value = false
            errorMsg.value = "Please enter valid data"
            return false
        }
        return true
    }

}