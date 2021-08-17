package com.task.noteapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    if (imgUrl.isNullOrEmpty()) {
        imgView.visibility = View.GONE
    } else {
        imgView.visibility = View.VISIBLE
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions().placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("status")
fun bindStatus(statusImageView: ImageView, status: LoadingStatus?) {
    when(status) {
        LoadingStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)

        }
        LoadingStatus.DONE, LoadingStatus.ERROR -> {
            statusImageView.visibility = View.GONE

        }
    }
}