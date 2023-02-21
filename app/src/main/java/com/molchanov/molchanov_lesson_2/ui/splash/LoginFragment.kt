package com.molchanov.molchanov_lesson_2.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanov_lesson_2.databinding.FragmentLoginBinding
import com.molchanov.molchanov_lesson_2.ui.base.BaseFragment
import com.molchanov.molchanov_lesson_2.ui.main.MainActivity

class LoginFragment : BaseFragment() {

    companion object {
        val instance = LoginFragment()
        const val FRAGMENT_TAG = "LoginFragment"
    }

    override val binding: FragmentLoginBinding
    get() = _binding as FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        startActivity(Intent(requireContext(), MainActivity::class.java))

        return binding.root
    }
}