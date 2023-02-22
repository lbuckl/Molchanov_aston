package com.molchanov.molchanov_lesson_2.ui.splash

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
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

        initPasswordChecker()

        initButtonEnter()

        return binding.root
    }

    /**
     * Работа кнопки Войти во фрагменте
     */
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

            btnEnter.isClickable = false
        }
    }

    /**
     * Функция мутит и отображает кнопку
     * если количество символов меньше 5 или больше 20
     */
    private fun initPasswordChecker(){
        with(binding){
            tilPassword.counterTextColor = ColorStateList.valueOf(Color.RED)
            tilPassword.setHelperTextColor(ColorStateList.valueOf(Color.RED))

            etPassword.addTextChangedListener {
                it?.let { text ->
                    if (text.length in 5..20){
                        btnEnter.alpha = 1.0F
                        btnEnter.isClickable = true
                        tilPassword.counterTextColor = ColorStateList.valueOf(Color.BLACK)
                        tilPassword.setHelperTextColor(ColorStateList.valueOf(Color.BLACK))
                    }
                    else {
                        btnEnter.alpha = 0.5F
                        btnEnter.isClickable = false
                        tilPassword.counterTextColor = ColorStateList.valueOf(Color.RED)
                        tilPassword.setHelperTextColor(ColorStateList.valueOf(Color.RED))
                    }
                }
            }
        }
    }
}