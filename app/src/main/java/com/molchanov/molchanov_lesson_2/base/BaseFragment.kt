package com.molchanov.molchanov_lesson_2.base

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.molchanov.molchanov_lesson_2.clock.IClockView

abstract class BaseFragment: Fragment() {

    protected var _binding: ViewBinding? = null

    abstract val binding: ViewBinding
}