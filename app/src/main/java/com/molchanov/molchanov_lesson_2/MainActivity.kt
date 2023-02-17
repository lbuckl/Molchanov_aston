package com.molchanov.molchanov_lesson_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)
    }
}