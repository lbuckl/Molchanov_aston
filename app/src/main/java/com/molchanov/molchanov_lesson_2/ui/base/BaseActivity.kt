package com.molchanov.molchanov_lesson_2.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = getViewBinding()

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setMainFragment(savedInstanceState)
    }

    abstract fun getViewBinding(): T

    /**
     * Установка базового фрагмента в контейнер при запуске приложения
     */
    private fun setMainFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            addMainFragment()
    }

    /**
     * Здесь необходимо реализовать добавление первого фрагмента в контейнер
     */
    abstract fun addMainFragment()
}