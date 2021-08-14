package com.task.noteapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.noteapp.data.dto.NoteDTO


/**
 * The Room Database that contains the notes table.
 */
@Database(entities = [NoteDTO::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun notesDao(): NotesDao

}