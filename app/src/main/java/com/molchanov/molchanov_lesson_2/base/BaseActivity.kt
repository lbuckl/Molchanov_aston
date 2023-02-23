package com.molchanov.molchanov_lesson_2.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding

abstract class BaseActivity: AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setMainFragment(savedInstanceState)
    }

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