package com.molchanov.molchanov_lesson_2.ui.main.offices

import androidx.lifecycle.LiveData
import com.molchanov.molchanov_lesson_2.domain.OfficesInfo
import com.molchanov.molchanov_lesson_2.model.IRepository
import com.molchanov.molchanov_lesson_2.ui.base.BaseViewModel

/**
 * Вью модель фрагмента со списком офистов ASTON
 */
class OfficesViewModel(private val repository: IRepository) : BaseViewModel<OfficesAppState>() {

    private var lastOfficesInfo: OfficesInfo? = null

    fun getMyLiveData(): LiveData<OfficesAppState> {
        setData()
        return liveData
    }

    private fun setData() {
        try {
            liveData.postValue(OfficesAppState.Success(repository.getOfficesInfo()))
        } catch (e: java.lang.IndexOutOfBoundsException) {
            e.printStackTrace()
            liveData.postValue(OfficesAppState.Error("Get data error"))
        }
    }

    fun setOfficeInfoData(data: OfficesInfo) {
        liveData.postValue(OfficesAppState.ClickData(data))
        lastOfficesInfo = data
    }
}