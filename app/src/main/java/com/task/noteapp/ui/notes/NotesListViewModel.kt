package com.task.noteapp.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.task.noteapp.data.NoteDataSource
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.data.dto.Result
import com.task.noteapp.model.NoteDataItem
import com.task.noteapp.util.LoadingStatus
import kotlinx.coroutines.launch

class NotesListViewModel(
    app: Application,
    private val dataSource: NoteDataSource
) : AndroidViewModel(app) {

    val notesList = MutableLiveData<List<NoteDataItem>>()
    val errorMessage = MutableLiveData<String>()
    val showNoData: MutableLiveData<Boolean> = MutableLiveData()
    val status = MutableLiveData<LoadingStatus>()


    fun getNotes() {
        viewModelScope.launch {

            status.value = LoadingStatus.LOADING
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
                    showNoData.value = dataList.isEmpty()
                    status.value = LoadingStatus.DONE
                }
                is Result.Error -> {
                    errorMessage.value = result.message
                    showNoData.value = true
                    status.value = LoadingStatus.ERROR
                }
            }
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch {
            status.value = LoadingStatus.LOADING
            dataSource.deleteNoteById(id)
            //refresh list after delete
            getNotes()
            status.value = LoadingStatus.DONE

        }
    }
}