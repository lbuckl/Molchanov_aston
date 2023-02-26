package com.molchanov.molchanov_lesson_2.ui.main.offices

import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.ui.base.AppState

sealed class OfficesAppState : AppState() {
    data class Success<T>(val data: List<T>) : OfficesAppState()
    data class ClickData(val office: OfficesInfo) : OfficesAppState()
    data class Error(val errorMsg: String) : OfficesAppState()
}