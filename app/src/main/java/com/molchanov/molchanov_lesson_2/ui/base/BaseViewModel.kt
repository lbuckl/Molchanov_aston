package com.molchanov.molchanov_lesson_2.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<V: AppState>(
    val liveData: MutableLiveData<V> = MutableLiveData<V>()
): ViewModel() {

    /*fun getMutableLiveData(): MutableLiveData<V>{
        setData()
        return liveData
    }*/

    //abstract fun setData()

}