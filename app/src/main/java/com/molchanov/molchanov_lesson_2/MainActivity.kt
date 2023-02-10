package com.molchanov.molchanov_lesson_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inintButtons()
    }

    private fun inintButtons(){
        binding.buttonNewActivity.setOnClickListener {
            val newActivityIntent = Intent(this, SecondActivity::class.java)

            startActivity(newActivityIntent)
        }
    }
}