package com.molchanov.molchanov_lesson_2

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.molchanov.molchanov_lesson_2.base.BaseFragment
import com.molchanov.molchanov_lesson_2.databinding.FragmentMainBinding
import kotlinx.coroutines.*
import java.time.LocalTime
import java.time.ZoneId

/**
 * Фрагмент с отображением часов
 */
class MainFragment : BaseFragment() {

    companion object {
        val instance = MainFragment()

        private const val HOUR_CHIP_FLAG = 1
        private const val MINUTE_CHIP_FLAG = 2
        private const val SECOND_CHIP_FLAG = 3
    }

    private val coroutineClock = CoroutineScope(Dispatchers.Default)

    private var selectedTimeChip = 0

    override val binding: FragmentMainBinding
        get() = _binding!! as FragmentMainBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkTime()

        initSelectColorButtons()
    }

    /**
     * Функция запускает слежение за системным временем и сетит его в ClockView
     */
    private fun checkTime() {
        coroutineClock.launch {
            while (true) {
                LocalTime.now(ZoneId.of("Europe/Moscow")).let {
                    binding.watch.setTime(it.hour, it.minute, it.second)
                }
                delay(1000)
            }
        }
    }

    private fun initSelectColorButtons() {
        with(binding) {

            chipHour.setOnClickListener {
                selectedTimeChip = HOUR_CHIP_FLAG
                chipGroup.visibility = View.VISIBLE
            }

            chipMinute.setOnClickListener {
                selectedTimeChip = MINUTE_CHIP_FLAG
                chipGroup.visibility = View.VISIBLE
            }

            chipSecond.setOnClickListener {
                selectedTimeChip = SECOND_CHIP_FLAG
                chipGroup.visibility = View.VISIBLE
            }

            //_______________________________________
            chipBlack.setOnClickListener {
                setClockArrowColor(selectedTimeChip, Color.BLACK)
                chipGroup.visibility = View.GONE
            }

            chipRed.setOnClickListener {
                setClockArrowColor(selectedTimeChip, Color.RED)
                chipGroup.visibility = View.GONE
            }

            chipGreen.setOnClickListener {
                setClockArrowColor(selectedTimeChip, Color.GREEN)
                chipGroup.visibility = View.GONE
            }

            chipBlue.setOnClickListener {
                setClockArrowColor(selectedTimeChip, Color.BLUE)
                chipGroup.visibility = View.GONE
            }
        }
    }

    /**
     * Функция задаёт цвет стрелки часов
     */
    private fun setClockArrowColor(timeFlag: Int, color: Int) {
        with(binding.watch) {
            when (timeFlag) {
                HOUR_CHIP_FLAG -> {
                    setHourColorsForArrows(color)
                }
                MINUTE_CHIP_FLAG -> {
                    setMinuteColorsForArrows(color)
                }
                SECOND_CHIP_FLAG -> {
                    setSecondColorsForArrows(color)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
        coroutineClock.cancel()
    }
}