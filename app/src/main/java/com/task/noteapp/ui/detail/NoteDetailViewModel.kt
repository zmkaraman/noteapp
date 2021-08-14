package com.task.noteapp.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.NoteDataSource
import kotlinx.coroutines.launch

class NoteDetailViewModel (app: Application,
                           private val dataSource: NoteDataSource
) : AndroidViewModel(app) {


    val navigateBackToList = MutableLiveData<Boolean>()
    val errorMsg = MutableLiveData<String>()

    fun deleteNote(id: String) {
        viewModelScope.launch {
            dataSource.deleteNoteById(id)
        }
        navigateBackToList.value = true
    }

}