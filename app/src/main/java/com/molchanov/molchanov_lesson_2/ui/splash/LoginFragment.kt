package com.molchanov.molchanov_lesson_2.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.molchanov.molchanov_lesson_2.R
import com.molchanov.molchanov_lesson_2.databinding.FragmentLoginBinding
import com.molchanov.molchanov_lesson_2.ui.base.BaseFragment
import com.molchanov.molchanov_lesson_2.ui.main.MainActivity

/**
 * Фрагмент для ввода логина и паролья пользователем
 */
class LoginFragment : BaseFragment() {

    companion object {
        val instance = LoginFragment()
        const val FRAGMENT_TAG = "LoginFragment"
    }

    private val login = "User"
    private val pass = "123qwe"

    override val binding: FragmentLoginBinding
    get() = _binding as FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        //initButtonEnter()
        startActivity(Intent(requireContext(), MainActivity::class.java))

        return binding.root
    }

    private fun initButtonEnter(){
        with(binding){
            btnEnter.setOnClickListener { btn->

                if (etLogin.text.toString() == login && etPassword.text.toString() == pass){

                    startActivity(Intent(requireContext(), MainActivity::class.java))

                    requireActivity().finish()
                }
                else{
                    Snackbar.make(
                        binding.btnEnter,
                        requireContext().getText(R.string.wrong_pass),
                        Snackbar.LENGTH_SHORT)
                        .setAnchorView(binding.btnEnter)
                        .show()
                }
            }
        }
    }
}