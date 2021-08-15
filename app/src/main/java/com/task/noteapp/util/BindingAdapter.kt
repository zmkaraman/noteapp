package com.task.noteapp.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.task.noteapp.R


@BindingAdapter("formatUpdateTag")
fun bindUpdateTagText(textView: TextView, dateStr: String?) {
    dateStr?.let {
        textView.text = textView.resources.getString(R.string.note_date_updated, dateStr)
    }
}


@BindingAdapter("formatCreateTag")
fun bindCreateTagText(textView: TextView, dateStr: String?) {
    dateStr?.let {
        textView.text = textView.resources.getString(R.string.note_date_created, dateStr)
    }
}