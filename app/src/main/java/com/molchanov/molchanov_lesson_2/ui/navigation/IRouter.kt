package com.molchanov.molchanov_lesson_2.ui.navigation

import androidx.fragment.app.Fragment

interface IRouter {

    fun addFragment(fragmentRepId: Int, fragment: Fragment, tag: String)

    fun replaceFragment(fragmentRepId: Int, fragment: Fragment, tag: String)
}