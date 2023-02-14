package com.molchanov.molchanov_lesson_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Функция проверяет создан ли уже франмент для замены
 * Если да - то меняет фрагмент в контейнере на ранее созданный
 * Если нет - то меняет фрагмент в контейнере на вновь созданный
 */
fun replaceFragment(
    fragmentRepId: Int,
    fragmentManager: FragmentManager,
    fragment: Fragment,
    tag: String
    ){

    //Поиск старого фрагмента в менеджере по тегу
    val oldFragment = fragmentManager.findFragmentByTag(tag)

    if (oldFragment == null){
        fragmentManager.beginTransaction()
            .replace(fragmentRepId,fragment,tag)
            .addToBackStack(tag)
            .commit()
    }
    else {
        fragmentManager.beginTransaction()
            .replace(fragmentRepId,oldFragment,tag)
            .commit()
    }
}