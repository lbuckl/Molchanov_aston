package com.molchanov.molchanov_lesson_2.ui.base

sealed class AppState {
    data class Success<T>(val data: List<T>) :AppState()
    data class Error(val errorMsg: String) :AppState()
}