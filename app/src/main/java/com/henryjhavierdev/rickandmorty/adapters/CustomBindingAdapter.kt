package com.henryjhavierdev.rickandmorty.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.henryjhavierdev.imagemanager.bindCircularImageUrl
import com.henryjhavierdev.imagemanager.bindImageUrl
import com.henryjhavierdev.rickandmorty.R


@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
        view.bindImageUrl(
            url = imageUrl,
            placeholder = R.drawable.ic_camera_alt_black,
            errorPlaceholder = R.drawable.ic_broken_image_black
        )
}

@BindingAdapter("imageCircleFromUrl")
fun bindImageCircleFromUrl(view: ImageView, imageUrl: String?) {
    view.bindCircularImageUrl(
        url = imageUrl,
        placeholder = R.drawable.ic_camera_alt_black,
        errorPlaceholder = R.drawable.ic_broken_image_black
    )
}
