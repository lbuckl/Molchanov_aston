package com.molchanov.molchanov_lesson_2.domain

import android.text.SpannableString

data class VacancyInfo(
    val id: Int,
    var title: SpannableString,
    var description: SpannableString
)