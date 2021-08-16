package com.task.noteapp.model

import java.io.Serializable
import java.util.*

/**
 * data class acts as a data mapper between the DB and the UI
 */
data class NoteDataItem(
    var title: String?,
    var description: String?,
    var createDate: String?,
    var updateDate: String?,
    var imageUrl: String?,
    val id: String = UUID.randomUUID().toString()
) : Serializable
