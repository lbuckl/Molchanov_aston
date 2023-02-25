package com.molchanov.molchanov_lesson_2.ui.base

import com.molchanov.molchanov_lesson_2.domain.OfficesInfo

sealed class OfficeAppState : AppState() {
    data class Success<T>(val data: List<T>) : OfficeAppState()
    data class ClickData(val office: OfficesInfo) : OfficeAppState()
    data class Error(val errorMsg: String) : OfficeAppState()
}