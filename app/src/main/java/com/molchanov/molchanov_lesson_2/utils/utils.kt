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

/**
 * Функция ищет слово в строке возвращает true, если найдено
 * @param text - текст для поиска
 * @param find - слово для поиска
 */
fun findWordInTextCount(text: String, find: String): Boolean {
    return Regex(find, RegexOption.IGNORE_CASE).findAll(text).toList().isNotEmpty()
}