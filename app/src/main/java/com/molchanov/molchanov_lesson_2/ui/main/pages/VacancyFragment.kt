package com.molchanov.molchanov_lesson_2.ui.main.pages

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanov_lesson_2.databinding.FragmentVacancyBinding
import com.molchanov.molchanov_lesson_2.ui.base.BaseFragment


class VacancyFragment() : BaseFragment() {

    companion object {
        val instance = VacancyFragment()
        const val FRAGMENT_TAG = "VacancyFragment"
    }

    override val binding: FragmentVacancyBinding
        get() = _binding as FragmentVacancyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacancyBinding.inflate(inflater, container, false)

        Log.v("@@@", "VacancyFragment")

        return binding.root
    }
}