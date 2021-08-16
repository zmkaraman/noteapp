package com.task.noteapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.NoteDataSource
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.model.NoteDataItem
import kotlinx.coroutines.launch

class NoteDetailViewModel (app: Application,
                           private val dataSource: NoteDataSource
) : AndroidViewModel(app) {


    val navigateBackToList = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()

    fun validateAndUpdateNote(note: NoteDataItem) {
        if (validateEnteredData(note)) {
            updateNote(note)
        }
    }


    fun deleteNote(id: String) {
        viewModelScope.launch {
            dataSource.deleteNoteById(id)
        }
        navigateBackToList.value = true
    }

    private fun updateNote(note: NoteDataItem) {
        viewModelScope.launch {
            val rowCount = dataSource.updateNote(NoteDTO(
                note.title,
                note.description,
                note.createDate,
                note.updateDate,
                note.imageUrl,
                note.id
            ))
        }
        navigateBackToList.value = true
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