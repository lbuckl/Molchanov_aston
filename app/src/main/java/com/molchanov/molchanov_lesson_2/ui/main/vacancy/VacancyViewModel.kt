package com.molchanov.molchanov_lesson_2.ui.main.vacancy

import androidx.lifecycle.LiveData
import com.molchanov.molchanov_lesson_2.model.IRepository
import com.molchanov.molchanov_lesson_2.ui.base.BaseViewModel

/**
 * Вью модель фрагмента со списком вакансий ASTON
 */
class VacancyViewModel(private val repository: IRepository) : BaseViewModel<VacancyAppState>() {

    fun getMyLiveData(): LiveData<VacancyAppState> {
        setData()
        return liveData
    }

    private fun setData() {
        try {
            liveData.postValue(VacancyAppState.Success(repository.getVacancyInfo()))
        } catch (e: java.lang.IndexOutOfBoundsException) {
            e.printStackTrace()
            liveData.postValue(VacancyAppState.Error("Get data error"))
        }
    }
}