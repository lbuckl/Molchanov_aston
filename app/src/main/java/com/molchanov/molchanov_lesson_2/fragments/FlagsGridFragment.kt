package com.molchanov.molchanov_lesson_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.molchanov.molchanov_lesson_2.databinding.FragmentFlagsGridBinding

class FlagsGridFragment: Fragment() {

    companion object{
        val instance = FlagsGridFragment()
    }

    private var _binding: FragmentFlagsGridBinding? = null
    private val binding: FragmentFlagsGridBinding
    get(){
        return _binding!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlagsGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(this.id,FlagsFrameFragment.instance,"flags_frame")
                .addToBackStack("flags_grid")
                .commit()
        }

        Snackbar.make(binding.root,"Grid", Snackbar.LENGTH_SHORT).show()
    }
}