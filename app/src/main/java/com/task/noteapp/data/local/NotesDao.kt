package com.task.noteapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.task.noteapp.data.dto.NoteDTO

/**
 * Data Access Object for the notes table.
 */
@Dao
interface NotesDao {

    @Query("SELECT * FROM notes")
    suspend fun getNotes(): List<NoteDTO>

    @Query("SELECT * FROM notes where id = :id")
    suspend fun getNoteById(id: String): NoteDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: NoteDTO)

    @Update
    suspend fun updateNote(note: NoteDTO): Int

    @Query("DELETE FROM notes where id = :id")
    suspend fun deleteNoteById(id: String)

    @Query("DELETE FROM notes")
    suspend fun deleteAllNotes()
}