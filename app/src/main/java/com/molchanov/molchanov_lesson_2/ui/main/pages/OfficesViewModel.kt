package com.molchanov.molchanov_lesson_2.ui.main.pages

import androidx.lifecycle.LiveData
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.model.IRepository
import com.molchanov.molchanov_lesson_2.ui.base.BaseViewModel
import com.molchanov.molchanov_lesson_2.ui.base.OfficeAppState

/**
 * Вью модель фрагмента со списком офистов ASTON
 */
class OfficesViewModel(private val repository: IRepository) : BaseViewModel<OfficeAppState>() {

    fun getMyLiveData(): LiveData<OfficeAppState> {
        setData()
        return liveData
    }

    private fun setData() {
        try {
            liveData.postValue(OfficeAppState.Success(repository.getOfficesInfo()))
        }catch (e: java.lang.IndexOutOfBoundsException){
            e.printStackTrace()
            liveData.postValue(OfficeAppState.Error("Get data error"))
        }
    }

    fun setOfficeInfoData(data: OfficesInfo) {
        liveData.postValue(OfficeAppState.ClickData(data))
    }

}