package com.molchanov.molchanov_lesson_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.molchanov.molchanov_lesson_2.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {

    companion object {
        const val SAVED_TEXT_KEY = "text_key"
    }


    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.v("@@@","onSaveInstanceState")
        binding.textLayout.text.toString().also {
            Log.v("@@@",it)
            if (it.isNotBlank()){
                outState.putString(SAVED_TEXT_KEY,it)
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.v("@@@","onRestoreInstanceState")
        val result = savedInstanceState.getString(SAVED_TEXT_KEY, "")
        binding.textLayout.setText(result)
        super.onRestoreInstanceState(savedInstanceState)
    }
}