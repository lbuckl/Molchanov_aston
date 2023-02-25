package com.molchanov.molchanov_lesson_2.ui.base

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment: Fragment() {

    protected var _binding: ViewBinding? = null

    abstract val binding: ViewBinding
}