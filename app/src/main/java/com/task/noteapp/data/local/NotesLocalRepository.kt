package com.task.noteapp.data.local

import com.task.noteapp.data.NoteDataSource
import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.data.dto.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesLocalRepository(
    private val notesDao: NotesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteDataSource {

    override suspend fun getNotes(): Result<List<NoteDTO>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(notesDao.getNotes())
        } catch (ex: Exception) {
            Result.Error(ex.localizedMessage)
        }
    }

    override suspend fun getNoteById(id: String): Result<NoteDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun addNote(note: NoteDTO) {
        notesDao.addNote(note)
    }


    override suspend fun deleteNoteById(id: String): Result<NoteDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNotes() {
        TODO("Not yet implemented")
    }


}