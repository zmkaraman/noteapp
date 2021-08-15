package com.task.noteapp

import com.task.noteapp.data.NoteDataSource
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.data.dto.Result

//Use FakeDataSource that acts as a test double to the LocalDataSource
class FakeDataSource(var notes: MutableList<NoteDTO>? = mutableListOf() ) : NoteDataSource {

    private var shouldReturnError = false
    private var shouldReturnMockData = false
    private var noteServiceData: MutableList<NoteDTO> = mutableListOf()

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    fun setMockData(value: Boolean) {
        shouldReturnMockData = value
    }

    fun addNotes(note: NoteDTO) {
        noteServiceData.add(note)
    }

    override suspend fun getNotes(): Result<List<NoteDTO>> {
        if (shouldReturnError) {
            return Result.Error("Test exception", -1)
        }
        if (shouldReturnMockData) {
            return return Result.Success(ArrayList(noteServiceData))
        }
        notes?.let { return Result.Success(ArrayList(it)) }
        return Result.Error("Tasks not found", 101)    }

    override suspend fun addNote(note: NoteDTO) {
        notes?.add(note)
    }

    override suspend fun updateNote(note: NoteDTO): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNoteById(id: String) {
        TODO("Not yet implemented")
    }

}