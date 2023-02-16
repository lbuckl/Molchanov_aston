package com.molchanov.molchanov_lesson_2

class MainActivity : BaseActivity() {

    override fun addMainFragment() {
        supportFragmentManager.beginTransaction()
            .add(binding.container.id, MainFragment.instance, "flags_frame")
            .commit()
    }
}