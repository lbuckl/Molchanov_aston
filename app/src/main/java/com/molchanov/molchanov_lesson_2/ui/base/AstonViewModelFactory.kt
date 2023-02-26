package com.molchanov.molchanov_lesson_2.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molchanov.molchanov_lesson_2.model.Repository
import com.molchanov.molchanov_lesson_2.ui.main.offices.OfficesViewModel
import com.molchanov.molchanov_lesson_2.ui.main.vacancy.VacancyViewModel

object AstonViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    private lateinit var repo: Repository

    private val viewModelMap = HashMap<Class<*>, ViewModel>()

    fun setRepository(repository: Repository) {
        repo = repository
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            OfficesViewModel::class.java -> {
                if (viewModelMap[modelClass] == null) {
                    val model = OfficesViewModel(repo)
                    viewModelMap[modelClass] = model
                    model as T
                } else viewModelMap[modelClass] as T
            }
            VacancyViewModel::class.java -> {
                if (viewModelMap[modelClass] == null) {
                    val model = VacancyViewModel(repo)
                    viewModelMap[modelClass] = model
                    model as T
                } else viewModelMap[modelClass] as T
            }
            else -> super.create(modelClass)
        }
    }
}