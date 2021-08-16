package com.task.noteapp.util

import java.text.SimpleDateFormat
import java.util.*

object AppUtil {

    fun getSystemTimeDate(): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return sdf.format(Date())
    }
}