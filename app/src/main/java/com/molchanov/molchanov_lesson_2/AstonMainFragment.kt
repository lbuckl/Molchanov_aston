package com.molchanov.molchanov_lesson_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.molchanov.molchanov_lesson_2.databinding.FragmentAstonMainBinding

class AstonMainFragment: Fragment() {

    companion object{
        val instance = AstonMainFragment()
    }

    private var _binding: FragmentAstonMainBinding? = null
    private val binding: FragmentAstonMainBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAstonMainBinding.inflate(inflater, container, false)
        return binding.root
    }
}