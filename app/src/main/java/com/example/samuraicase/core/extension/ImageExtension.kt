package com.example.samuraicase.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.samuraicase.R


fun ImageView.glideWithExtensions(url: String?) {
    val options = RequestOptions()
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}