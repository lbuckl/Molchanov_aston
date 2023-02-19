package com.molchanov.molchanov_lesson_2

import com.molchanov.molchanov_lesson_2.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun addMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, MainFragment.instance, "flags_frame")
            .commit()
    }
}