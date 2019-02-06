package com.altran.ibanarriola.teamworktest.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bind:photoFilePath")
fun loadPhotoFilePath(itemView: ImageView, url: String) {
    Glide.with(itemView)
            .load(url)
            .into(itemView)
}

@BindingAdapter("bind:roundPhotoFilePath")
fun loadRoundPhotoFilePath(itemView: ImageView, url: String) {
    Glide.with(itemView)
            .load(url)
            .apply(RequestOptions().circleCrop())
            .into(itemView)
}