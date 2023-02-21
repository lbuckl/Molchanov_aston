package com.molchanov.molchanov_lesson_2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.animation.CycleInterpolator
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.molchanov.molchanov_lesson_2.databinding.ActivitySplashBinding
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashActivityAston : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val coroutine = CoroutineScope(Dispatchers.Main)

    private val animationDelay = 2000L
    private val startDelay = 500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)

        showSplash()
    }

    /**
     * Функция отображения текста с анимацией печати
     */
    private fun showSplash(){
        coroutine.launch {
            delay(startDelay)

            val text = SpannableString(this@SplashActivityAston
                .getString(R.string.aston_intensive_splash_text))

            val printSpeedDelayList = printSpeedDelay(text, animationDelay/text.length)


            try {
                for (index in text.indices){
                    text.setSpan(
                        ForegroundColorSpan(theme.resources.getColor(R.color.black,theme)),
                        index, index + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    delay(printSpeedDelayList[index])
                    binding.textView.text = text
                }
            }catch (e: java.lang.IndexOutOfBoundsException){
                e.printStackTrace()

                with(binding.textView){

                    setTextColor(theme.resources.getColor(R.color.black,theme))

                    this.text = this@SplashActivityAston
                        .getString(R.string.aston_intensive_splash_text)

                    delay(startDelay)
                }
            }

            delay(startDelay)

            startActivity(Intent(this@SplashActivityAston, MainActivity::class.java))
        }
    }

    /**
     * Функция изменяет скорость печати, делая его походжим на реальную
     */
    private fun printSpeedDelay(text: SpannableString, defaultDelay: Long): List<Long> {

        var delayIndex = 1

        val result = mutableListOf<Long>()

        text.forEach {

            if (it in ' '..'@') result.add(defaultDelay * 3)
            else{
                result.add(defaultDelay + defaultDelay/delayIndex)

                delayIndex ++

                if (delayIndex == 5) delayIndex = 1
            }
        }
        return result
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutine.cancel()
    }
}