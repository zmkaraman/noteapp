package com.task.noteapp.data

import com.task.noteapp.data.dto.NoteDTO
import com.task.noteapp.data.dto.Result


/**
 * Main entry point for accessing notes data.
 */
interface NoteDataSource {
    suspend fun getNotes(): Result<List<NoteDTO>>
    suspend fun getNoteById(id: String): Result<NoteDTO>
    suspend fun addNote(note: NoteDTO)
    suspend fun deleteNoteById(id: String): Result<NoteDTO>
    suspend fun deleteAllNotes()
}