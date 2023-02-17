package com.molchanov.molchanov_lesson_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding
import com.molchanov.molchanov_lesson_2.fragments.FlagsFrameFragment

/**
 * Основное активити
 */
class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setMainFragment()
    }

    /**
     * Установка базового фрагмента в контейнер при запуске приложения
     */
    private fun setMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, FlagsFrameFragment.instance, "flags_frame")
            .commit()
    }
}