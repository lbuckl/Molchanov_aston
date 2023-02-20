package com.molchanov.molchanov_lesson_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanov_lesson_2.base.BaseFragment
import com.molchanov.molchanov_lesson_2.databinding.FragmentMainBinding
import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.ZoneId

class MainFragment: BaseFragment() {

    companion object {
        val instance = MainFragment()
    }

    private val coroutineClock = CoroutineScope(Dispatchers.Default)

    override val binding: FragmentMainBinding
        get() = _binding!!as FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coroutineClock.launch {
            while (true){
                LocalTime.now(ZoneId.of("Europe/Moscow")).let {
                    binding.watch.setTime(it.hour, it.minute, it.second)
                }
                delay(1000)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
        coroutineClock.cancel()
    }
}