package com.molchanov.molchanov_lesson_2.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Класс навигации по фрагментам
 * @param fragmentRepId - id фрагмента или сслыка на контейнер
 * @param fragment - фрагмент для замены
 * @param tag - тэг под которым был сохранён фрагмент
 */
class Router(private val fragmentManager: FragmentManager): IRouter {

    override fun addFragment(fragmentRepId: Int, fragment: Fragment, tag: String) {

        val oldFragment = fragmentManager.findFragmentByTag(tag)

        if (oldFragment == null) {
            fragmentManager.beginTransaction()
                .add(fragmentRepId, fragment, tag)
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(fragmentRepId, oldFragment, tag)
                .commit()
        }
    }

    override fun replaceFragment(fragmentRepId: Int, fragment: Fragment, tag: String) {

        val oldFragment = fragmentManager.findFragmentByTag(tag)

        if (oldFragment == null) {
            fragmentManager.beginTransaction()
                .replace(fragmentRepId, fragment, tag)
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(fragmentRepId, oldFragment, tag)
                .commit()
        }
    }

    fun replaceFragmentWithMessage(fragmentRepId: Int, fragment: Fragment, tag: String,
                                       message: Bundle){

        val oldFragment = fragmentManager.findFragmentByTag(tag)

        if (oldFragment == null) {
            fragmentManager.beginTransaction()
                .replace(fragmentRepId, fragment::class.java, message, tag)
                .commit()
        } else {
            fragmentManager.beginTransaction()
                .replace(fragmentRepId, oldFragment::class.java, message, tag)
                .commit()
        }
    }
}