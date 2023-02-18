package com.udacity.mawardy.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.udacity.mawardy.R


@BindingAdapter("imgUrl")
fun loadImageFromUrl(imageView: ImageView, url: String) {
//    Picasso.get().load(url).placeholder(R.drawable.ic_app_logo).into(imageView)
    Glide.with(imageView.context).load(url).placeholder(R.drawable.ic_app_logo).into(imageView)
}


