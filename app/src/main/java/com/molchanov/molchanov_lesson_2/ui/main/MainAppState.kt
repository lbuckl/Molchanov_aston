package com.molchanov.molchanov_lesson_2.ui.main

import android.view.MenuItem

/**
 * Класс состояний активити/фрагмента
 * Success - успешное выполнение
 * Error - неудачное завершение операции
 * Loading - состояние загрузки
 */
sealed class MainAppState {
    data class Success(val data: MenuItem) : MainAppState()
    data class Error(val error: Throwable) : MainAppState()
    data class Loading(val progress: Int) : MainAppState()
}