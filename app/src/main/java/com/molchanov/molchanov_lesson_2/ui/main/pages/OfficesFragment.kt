package com.molchanov.molchanov_lesson_2.ui.main.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficesBinding
import com.molchanov.molchanov_lesson_2.ui.base.BaseFragment

class OfficesFragment : BaseFragment() {

    companion object {
        val instance = OfficesFragment()
        const val FRAGMENT_TAG = "OfficesFragment"
    }

    override val binding: FragmentOfficesBinding
        get() = _binding as FragmentOfficesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficesBinding.inflate(inflater, container, false)

        return binding.root
    }
}