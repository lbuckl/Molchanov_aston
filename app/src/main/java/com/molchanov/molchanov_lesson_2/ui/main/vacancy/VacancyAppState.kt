package com.molchanov.molchanov_lesson_2.ui.main.vacancy

import com.molchanov.molchanov_lesson_2.ui.base.AppState

sealed class VacancyAppState : AppState() {
    data class Success<T>(val data: List<T>) : VacancyAppState()
    data class Error(val errorMsg: String) : VacancyAppState()
}