package com.molchanov.molchanov_lesson_2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.molchanov.molchanov_lesson_2.databinding.FragmentFlagsFrameBinding
import com.molchanov.molchanov_lesson_2.databinding.FragmentFlagsLinearBinding

class FlagsFrameFragment: Fragment() {

    companion object{
        val instance = FlagsFrameFragment()
    }

    private var _binding: FragmentFlagsFrameBinding? = null
    private val binding: FragmentFlagsFrameBinding
    get(){
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
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(this.id,FlagsLinearFragment.instance,"flags_linear")
                .addToBackStack("flags_frame")
                .commit()
       }

        Snackbar.make(binding.root,"Frame",Snackbar.LENGTH_SHORT).show()
    }
}