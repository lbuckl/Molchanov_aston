package com.molchanov.molchanov_lesson_2

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Функция загрузки изображния по URL с помощью Glide
 */
fun ImageView.loadImageFromUrl(url: Int) {
    Glide.with(context)
        .load(url)
        .placeholder(url)
        .into(this)
}