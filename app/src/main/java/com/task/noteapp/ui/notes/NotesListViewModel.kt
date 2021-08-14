package com.task.noteapp.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.NoteDataSource
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.data.dto.Result
import kotlinx.coroutines.launch

class NotesListViewModel(
    app: Application,
    private val dataSource: NoteDataSource
) : AndroidViewModel(app) {

    val notesList = MutableLiveData<List<NoteDataItem>>()
    val errorMessage = MutableLiveData<String>()

    fun getNotes() {
        viewModelScope.launch {
            //interacting with the dataSource has to be through a coroutine
            val result = dataSource.getNotes()
            when (result) {
                is Result.Success<*> -> {
                    val dataList = ArrayList<NoteDataItem>()
                    dataList.addAll((result.data as List<NoteDTO>).map { note ->
                        NoteDataItem(
                            note.title,
                            note.description,
                            note.createDate,
                            note.updateDate,
                            note.imageUrl,
                            note.id
                        )
                    })
                    notesList.value = dataList
                }
                is Result.Error ->
                    errorMessage.value = result.message
            }
        }
    }
}