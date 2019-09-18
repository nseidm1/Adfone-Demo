package com.noahseidman.adphone.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImageUrl(view: ImageView, imageUrl: String?) {
    if (imageUrl == null) {
        return
    }
    Picasso.get().load(imageUrl).into(view)
}