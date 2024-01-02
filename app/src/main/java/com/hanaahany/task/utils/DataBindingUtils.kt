package com.hanaahany.task.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import com.bumptech.glide.Glide

object DataBindingUtils{

}
@BindingAdapter("imageUrl","placeholder")
fun loadImage(itemView: ImageView, url:String, errorImg: Drawable){
    Glide.with(itemView.context).load(url)
        .error(errorImg)
        .into(itemView)
}

