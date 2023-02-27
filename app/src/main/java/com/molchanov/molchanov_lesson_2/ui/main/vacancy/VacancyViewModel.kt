package com.molchanov.molchanov_lesson_2.ui.main.vacancy

import android.graphics.Color
import android.text.Spannable
import androidx.lifecycle.LiveData
import com.molchanov.molchanov_lesson_2.domain.VacancyInfo
import com.molchanov.molchanov_lesson_2.findWordInText
import com.molchanov.molchanov_lesson_2.model.IRepository
import com.molchanov.molchanov_lesson_2.setSpanColorByWord
import com.molchanov.molchanov_lesson_2.ui.base.BaseViewModel

/**
 * Вью модель фрагмента со списком вакансий ASTON
 */
class VacancyViewModel(private val repository: IRepository) : BaseViewModel<VacancyAppState>() {

    init {
        getData()
    }

    fun getMyLiveData(): LiveData<VacancyAppState> {
        return liveData
    }

    private fun getData() {
        setData(repository.getVacancyInfo())
    }

    private fun setData(data: List<VacancyInfo>) {
        try {
            liveData.postValue(VacancyAppState.Success(data))
        } catch (e: java.lang.IndexOutOfBoundsException) {
            e.printStackTrace()

            liveData.postValue(VacancyAppState.Error("Get data error"))
        }
    }

    fun getFilteredData(text: String) {
        setData(getFilteredVacancyList(repository.getVacancyInfo(), text))
    }

    /**
     * Функция фильтрует список вакансий по ключевому слову
     */
    private fun getFilteredVacancyList(data: List<VacancyInfo>, search: String): List<VacancyInfo> {
        val result = mutableListOf<VacancyInfo>()

        data.forEach {
            if (findWordInText(it.title.toString(), search) ||
                findWordInText(it.description.toString(), search)
            ) {
                result.add(it)
            }
        }

        return setSpanOnText(result, search)
    }

    /**
     * Функция накладывает красный цвет на найденный текст в списке
     */
    private fun setSpanOnText(listData: List<VacancyInfo>, search: String): List<VacancyInfo> {
        listData.forEach {
            it.let { info ->
                info.title = setSpanColorByWord(
                    info.title, listOf(search),
                    Color.RED, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                info.description = setSpanColorByWord(
                    info.description, listOf(search),
                    Color.RED, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return listData
    }
}