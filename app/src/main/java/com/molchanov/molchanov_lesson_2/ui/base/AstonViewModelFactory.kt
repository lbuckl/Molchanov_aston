package com.molchanov.molchanov_lesson_2.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.molchanov.molchanov_lesson_2.model.Repository
import com.molchanov.molchanov_lesson_2.ui.main.pages.OfficesViewModel

class AstonViewModelFactory: ViewModelProvider.NewInstanceFactory() {

    private lateinit var repo: Repository

    fun setRepository(repository: Repository){
        repo = repository
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when(modelClass){
            OfficesViewModel::class.java ->{
                OfficesViewModel(repo) as T
            }
            else -> super.create(modelClass)
        }
    }
}