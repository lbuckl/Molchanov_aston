package com.molchanov.molchanov_lesson_2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.molchanov.molchanov_lesson_2.databinding.FragmentFlagsLinearBinding

class FlagsLinearFragment: Fragment() {

    companion object{
        val instance = FlagsLinearFragment()
    }

    private var _binding: FragmentFlagsLinearBinding? = null
    private val binding: FragmentFlagsLinearBinding
    get(){
        return _binding!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlagsLinearBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.root.setOnClickListener {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(this.id,FlagsGridFragment.instance,"flags_grid")
                .addToBackStack("flags_linear")
                .commit()
        }

        Snackbar.make(binding.root,"Linear", Snackbar.LENGTH_SHORT).show()
    }
}