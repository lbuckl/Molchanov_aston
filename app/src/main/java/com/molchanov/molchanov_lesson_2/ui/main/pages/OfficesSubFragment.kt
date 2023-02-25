package com.molchanov.molchanov_lesson_2.ui.main.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanov_lesson_2.databinding.FragmentOfficeInfoBinding
import com.molchanov.molchanov_lesson_2.ui.base.BaseFragment

/**
 * Фрагмент для отображения информации об офисе Aston
 */
class OfficesSubFragment : BaseFragment() {

    companion object {
        val instance = OfficesSubFragment()
        const val FRAGMENT_TAG = "OfficesSubFragment"
        const val BUNDLE_TAG = "OFFICE_MESSAGE_TAG"
    }

    override val binding: FragmentOfficeInfoBinding
        get() = _binding as FragmentOfficeInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficeInfoBinding.inflate(inflater, container, false)

        initFragment()

        return binding.root
    }

    private fun initFragment() {
        try {
            arguments?.getStringArray(BUNDLE_TAG)?.also {
                binding.tvCity.text = it[0]
                binding.tvInfo.text = it[1]
            }
        } catch (e: java.lang.IndexOutOfBoundsException) {
            e.printStackTrace()
        }
    }
}