package com.molchanov.molchanov_lesson_2

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
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
fun findWordInText(text: String, find: String): Boolean {
    return Regex(find, RegexOption.IGNORE_CASE).findAll(text).toList().isNotEmpty()
}

//region Функции для работы со span
/**
 * Функция ищет в [text] все слова списка[searchWords] и раскрашивает их в цвет [color]
 * @param color - цвет в формате int
 * @param spanFlag - флаг библиотеки Span (например Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
 * вовращает SpannableString
 */

fun setSpanColorByWord(
    text: SpannableString,
    searchWords: List<String>,
    color: Int,
    spanFlag: Int
): SpannableString {

    text.toString().apply {
        searchWords.forEach { word ->
            this.indexesOf(word, true).forEach {
                text.setSpan(
                    ForegroundColorSpan(
                        color
                    ),
                    it, it + word.length,
                    spanFlag
                )
            }
        }
    }

    return text
}

//Фунция ищет слово в тексте и возвращает массив первых индексов
fun String.indexesOf(substr: String, ignoreCase: Boolean = true): List<Int> =
    (if (ignoreCase) Regex(substr, RegexOption.IGNORE_CASE) else Regex(substr))
        .findAll(this).map { it.range.first }.toList()
//endregion