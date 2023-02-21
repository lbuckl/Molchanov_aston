package com.molchanov.molchanov_lesson_2.ui.main

import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Класс бизнес логики для MainActivity
 */
class MainViewModel(
    private val liveData: MutableLiveData<MainAppState> = MutableLiveData<MainAppState>()
) : ViewModel() {

    private var localMenuItem: MenuItem? = null

    fun getLiveData(): MutableLiveData<MainAppState> {

        setMenuItem(localMenuItem)

        return liveData
    }

    fun setMenuItem(menuItem: MenuItem?){
        localMenuItem = menuItem

        if (localMenuItem != null) liveData.postValue(MainAppState.Success(localMenuItem!!))
        else liveData.postValue(MainAppState.Error(Throwable("")))
    }
}