package com.molchanov.molchanov_lesson_2.ui.base

import com.molchanov.molchanov_lesson_2.ui.main.MainAppState

interface IViewModelRender {
    fun renderData(appState: MainAppState)
}