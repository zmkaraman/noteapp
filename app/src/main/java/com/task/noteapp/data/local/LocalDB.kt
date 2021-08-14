package com.task.noteapp.data.local

import android.content.Context
import androidx.room.Room

/**
 * Singleton class that is used to create a note db
 */
object LocalDB {
    /**
     * static method that creates a note class and returns the DAO of the note
     */
    fun createNotesDao(context: Context): NotesDao {
        return Room.databaseBuilder(
            context.applicationContext,
            NotesDatabase::class.java, "locationNotes.db"
        ).build().notesDao()
    }
}