package com.molchanov.molchanov_lesson_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanov_lesson_2.databinding.FragmentAstonMainBinding
import java.util.*

/**
 * Фрагмент отображения головной информации о компании Aston
 */
class AstonMainFragment() : BaseFragment() {

    companion object {
        val instance = AstonMainFragment()
    }

    override val binding: FragmentAstonMainBinding
        get() = _binding!! as FragmentAstonMainBinding


    private val location = Locale.getDefault()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAstonMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWelcomeImage()

        initButtons()
    }

    /**
     * Функция подгружает приветственное окно в зависимости от текущей локали
     */
    private fun initWelcomeImage() {
        val loc = location.toString().split("_")

        try {
            when (loc[0]) {
                "ru" -> binding.ivAstonWelcome.loadImageFromUrl(R.drawable.pic_aston_welcome_ru)
                "en" -> binding.ivAstonWelcome.loadImageFromUrl(R.drawable.pic_aston_welcome)
                else -> binding.ivAstonWelcome.loadImageFromUrl(R.drawable.pic_aston_welcome)
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            e.printStackTrace()
            binding.ivAstonWelcome.loadImageFromUrl(R.drawable.pic_aston_welcome)
        }
    }

    /**
     * Функция инициализирует кликкеры дял кнопок
     */
    private fun initButtons() {
        with(binding) {

            btnContacts.setOnClickListener {
                loading()
            }

            btnGallery.setOnClickListener {
                loading()
            }

            btnJobs.setOnClickListener {
                loading()
            }
        }
    }

    private fun loading(){
        with(binding){
            groupBottomInfo.visibility = View.GONE
            progressCircular.visibility = View.VISIBLE
        }
    }
}