package com.molchanov.molchanov_lesson_2

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.molchanov.molchanov_lesson_2.databinding.ActivityMainBinding

/**
 * Основное активити приложения
 */
class MainActivity : AppCompatActivity() {

    companion object {
        //константа для идентификации активити через интент
        const val INTENT_REQUEST_CODE = 101

        //константа для тхарения текста в Bundle
        const val SAVED_TEXT_KEY = "text_key"
    }

    private lateinit var binding: ActivityMainBinding

    private val activityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        Log.v("@@@", "registerForActivityResult")
        if (result.resultCode == Activity.RESULT_OK) {
            setExtraDataToTextView(result.data)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inintButtons()
    }

    /**
     * Функция инициализации кнопки открытия дочернего активити
     */
    private fun inintButtons() {
        binding.buttonNewActivity.setOnClickListener {

            val newActivityIntent = Intent(this, SecondActivity::class.java)

            /**
             * Если верися API выше 28 - выполняется версия запроса AndroidX Result API
             * если ниже - используем Deprecated startActivityForResult
             */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                activityResult.launch(newActivityIntent)
            } else {
                startActivityForResult(newActivityIntent, INTENT_REQUEST_CODE)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == INTENT_REQUEST_CODE && resultCode == RESULT_OK) {
            setExtraDataToTextView(data)
        }
    }

    /**
     * Функция отображения строковых данных полученных от дочерней активити
     * @param data - интент с данными от дочернего активити в виде текста
     */
    private fun setExtraDataToTextView(data: Intent?) {
        data?.getStringExtra(SecondActivity.SAVED_EDIT_TEXT_KEY)?.let {
            if (it.isNotBlank()) binding.textView.text = it
        }
    }

    //region функции сохранения и восстановления данных при перезагрузке активити
    /**
     * Функция сохранения состояния данных при перезагрузке активити
     */
    override fun onSaveInstanceState(outState: Bundle) {
        binding.textView.text.toString().also {
            if (it.isNotBlank()) {
                outState.putString(SAVED_TEXT_KEY, it)
            }
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * Функция восстановления состояния данных при перезагрузке активити
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.textView.text = savedInstanceState.getString(SAVED_TEXT_KEY, "")
        super.onRestoreInstanceState(savedInstanceState)
    }
    //endregion
}