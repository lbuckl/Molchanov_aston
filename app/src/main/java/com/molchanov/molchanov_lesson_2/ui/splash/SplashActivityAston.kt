package com.molchanov.molchanov_lesson_2.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import com.molchanov.molchanov_lesson_2.R
import com.molchanov.molchanov_lesson_2.databinding.ActivitySplashBinding
import com.molchanov.molchanov_lesson_2.ui.base.BaseActivity
import com.molchanov.molchanov_lesson_2.ui.main.MainActivity
import com.molchanov.molchanov_lesson_2.ui.navigation.Router
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashActivityAston : BaseActivity<ActivitySplashBinding>() {

    private val coroutine = CoroutineScope(Dispatchers.Main)

    private var router: Router? = null

    private val animationDelay = 1500L
    private val startDelay = 500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun addMainFragment() {
        showSplash()

        router = Router(supportFragmentManager)
    }

    /**
     * Функция отображения текста с анимацией печати
     */
    private fun showSplash() {
        coroutine.launch {
            delay(startDelay)

            val text = SpannableString(
                this@SplashActivityAston
                    .getString(R.string.aston_intensive_splash_text)
            )

            val printSpeedDelayList = printSpeedDelay(text, animationDelay / text.length)


            try {
                for (index in text.indices) {
                    text.setSpan(
                        ForegroundColorSpan(theme.resources.getColor(R.color.black, theme)),
                        index, index + 1,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    delay(printSpeedDelayList[index])
                    binding.textView.text = text
                }
            } catch (e: java.lang.IndexOutOfBoundsException) {
                e.printStackTrace()

                with(binding.textView) {

                    setTextColor(theme.resources.getColor(R.color.black, theme))

                    this.text = this@SplashActivityAston
                        .getString(R.string.aston_intensive_splash_text)

                    delay(startDelay)
                }
            }

            delay(startDelay)

            showLoginFragment()
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
            else {
                result.add(defaultDelay + defaultDelay / delayIndex)

                delayIndex++

                if (delayIndex == 5) delayIndex = 1
            }
        }
        return result
    }

    private fun showLoginFragment() {
        binding.textView.visibility = View.GONE
        binding.imageView.visibility = View.GONE

        router!!.addFragment(
            R.id.splash_container,
            LoginFragment.instance,
            LoginFragment.FRAGMENT_TAG
        )

        this.supportActionBar?.title = resources.getString(R.string.autorization)
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutine.cancel()
    }
}