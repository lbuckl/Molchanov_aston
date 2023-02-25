package com.molchanov.molchanov_lesson_2.ui.main.pages

import androidx.lifecycle.LiveData
import com.molchanov.molchanov_lesson_2.model.IRepository
import com.molchanov.molchanov_lesson_2.ui.base.AppState
import com.molchanov.molchanov_lesson_2.ui.base.BaseViewModel

class OfficesViewModel(private val repository: IRepository): BaseViewModel<AppState>() {

    fun getMyLiveData(): LiveData<AppState>{
        setData()
        return liveData
    }

    private fun setData() {
        liveData.postValue(AppState.Success(repository.getOfficesInfo()))
    }

}