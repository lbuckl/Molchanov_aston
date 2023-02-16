package com.molchanov.molchanov_lesson_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setMainFragment()
    }

    /**
     * Установка базового фрагмента в контейнер при запуске приложения
     */
    private fun setMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, AstonMainFragment.instance, "flags_frame")
            .commit()
    }
}