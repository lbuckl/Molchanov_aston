package com.molchanov.molchanov_lesson_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.molchanov.molchanov_lesson_2.databinding.FragmentFlagsFrameBinding
import com.molchanov.molchanov_lesson_2.replaceFragment

/**
 * Фрагмент для отображения в FrameLayout
 */
class FlagsFrameFragment : Fragment() {

    companion object {
        val instance = FlagsFrameFragment()
    }

    private var _binding: FragmentFlagsFrameBinding? = null
    private val binding: FragmentFlagsFrameBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlagsFrameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnClickListener {
            replaceFragment(
                this.id,
                requireActivity().supportFragmentManager,
                FlagsLinearFragment.instance,
                "flags_linear"
            )
        }

        Snackbar.make(binding.root, "Frame", Snackbar.LENGTH_SHORT).show()
    }
}