package com.molchanov.molchanov_lesson_2.clock

/**
 * Интерфейс для работы с ClockView
 */
interface IClockView {
    fun setTime(hour: Int, minute: Int, second: Int)

    fun setHourColorsForArrows(hourColor: Int)

    fun setMinuteColorsForArrows(minuteColor: Int)

    fun setSecondColorsForArrows(secondColor: Int)
}