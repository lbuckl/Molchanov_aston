package com.molchanov.molchanov_lesson_2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.molchanov.molchanov_lesson_2.databinding.ActivitySecondBinding

/**
 * Активтит для ввода текста и передачи данных в вызывающее активити
 */
class SecondActivity : AppCompatActivity() {

    companion object {
        //константа для тхарения текста в Bundle
        const val SAVED_EDIT_TEXT_KEY = "edit_text_key"
    }

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtons()
    }

    /**
     * Функция инициализации кнопки "Назад":
     * кнопка возвращает родительскому активити
     * значение введённое в EditTestView
     */

    private fun initButtons() {

        binding.buttonBack.setOnClickListener {
            Intent().let {
                it.putExtra(
                    SAVED_EDIT_TEXT_KEY,
                    binding.textLayout.text.toString()
                )
                setResult(RESULT_OK, it)
            }

            finish()
        }
    }

    //region функции сохранения и восстановления данных при перезагрузке активити
    /**
     * Функция сохранения состояния данных при перезагрузке активити
     */
    override fun onSaveInstanceState(outState: Bundle) {
        binding.textLayout.text.toString().also {
            if (it.isNotBlank()) {
                outState.putString(SAVED_EDIT_TEXT_KEY, it)
            }
        }
        super.onSaveInstanceState(outState)
    }

    /**
     * Функция восстановления состояния данных при перезагрузке активити
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.textLayout.setText(savedInstanceState.getString(SAVED_EDIT_TEXT_KEY, ""))
        super.onRestoreInstanceState(savedInstanceState)
    }
    //endregion
}