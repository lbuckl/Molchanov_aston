package com.molchanov.molchanov_lesson_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanov_lesson_2.base.BaseFragment
import com.molchanov.molchanov_lesson_2.databinding.FragmentMainBinding

class MainFragment : BaseFragment() {

    companion object {
        val instance = MainFragment()
    }

    override val binding: FragmentMainBinding
        get() = _binding!!as FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)

        return binding.root
    }
}