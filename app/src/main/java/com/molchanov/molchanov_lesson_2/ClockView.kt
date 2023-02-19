package com.molchanov.molchanov_lesson_2

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import kotlin.properties.Delegates

class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ClockViewStyle
) : View(context, attrs, defStyleAttr) {

    companion object{
        const val ARROW_HOUR_COLOR = Color.BLACK
        const val ARROW_MINUTE_COLOR = Color.BLACK
        const val ARROW_SECOND_COLOR = Color.RED
    }

    private var arrowHourColor by Delegates.notNull<Int>()
    private var arrowMinuteColor by Delegates.notNull<Int>()
    private var arrowSecondColor by Delegates.notNull<Int>()

    init {
        if (attrs == null) initDefaultAttributes()
        else initAttributes(attrs, defStyleAttr)
    }

    private fun initAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val attrArray: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ClockView,
            defStyleAttr,
            R.style.DefaultClockViewStyle
        )

        arrowHourColor = attrArray.getColor(R.styleable.ClockView_arrow_hour_color, ARROW_HOUR_COLOR)
        arrowMinuteColor = attrArray.getColor(R.styleable.ClockView_arrow_minute_color, ARROW_MINUTE_COLOR)
        arrowSecondColor = attrArray.getColor(R.styleable.ClockView_arrow_second_color, ARROW_SECOND_COLOR)

        attrArray.recycle()
    }

    private fun initDefaultAttributes() {
        arrowHourColor = ARROW_HOUR_COLOR
        arrowMinuteColor = ARROW_MINUTE_COLOR
        arrowSecondColor = ARROW_SECOND_COLOR
    }

    //Функция задания размеров
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    //Функция расположения элементов в кастом вью
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    //Бизнес-логика отрисовки
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}