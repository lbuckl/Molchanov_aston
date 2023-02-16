package com.molchanov.molchanov_lesson_2

import android.os.Bundle
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }

    override fun addMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, AstonMainFragment.instance)
            .commit()
    }
}