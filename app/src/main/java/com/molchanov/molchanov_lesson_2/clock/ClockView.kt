package com.molchanov.molchanov_lesson_2.clock

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.molchanov.molchanov_lesson_2.R
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

        const val NAME_HOUR = "Hour"
        const val NAME_MINUTE = "Minute"
        const val NAME_SECOND = "Second"

        const val ERROR_TIME_VALUE = -1
    }


    private var maxWidthPixels = resources.displayMetrics.widthPixels
    private var maxHeightPixel = resources.displayMetrics.heightPixels

    var hour: Int = 0
    set(value: Int){
        if (checkTimeInput(NAME_HOUR,value) == -1){
            catchTimeInputException(NAME_HOUR, value)
        }
        else {
            field = value
        }
    }

    var minute: Int = 3
        set(value: Int){
            if (checkTimeInput(NAME_MINUTE,value) == -1){
                catchTimeInputException(NAME_MINUTE, value)
            }
            else {
                field = value
            }
        }

    var second: Int = 6
        set(value: Int){
            if (checkTimeInput(NAME_SECOND,value) == -1){
                catchTimeInputException(NAME_SECOND, value)
            }
            else {
                field = value
                invalidate()
            }
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


    private val paintOval = Paint(Paint.ANTI_ALIAS_FLAG)

    //Функция задания размеров
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingBottom + paddingTop

        /*val sizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            100.0F,
            resources.displayMetrics
        )*/

        setMeasuredDimension(
            resolveSize(maxWidthPixels,widthMeasureSpec),
            resolveSize(maxHeightPixel,heightMeasureSpec)
        )
    }

    //Функция расположения элементов в кастом вью
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    //Бизнес-логика отрисовки
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paintOval.let {
            it.color = Color.BLACK
            it.style = Paint.Style.STROKE
            it.strokeWidth = resources.displayMetrics.density * 4
        }

        canvas?.drawOval(RectF(
            (maxWidthPixels/4).toFloat(),
            (maxHeightPixel/2 - maxWidthPixels/4).toFloat(),
            (maxWidthPixels - maxWidthPixels/4).toFloat(),
            (maxHeightPixel/2 + maxWidthPixels/4).toFloat()
        ),
            paintOval)

        paintOval.let {
            it.color = Color.RED
            it.style = Paint.Style.FILL
            it.strokeWidth = resources.displayMetrics.density * 4
        }

        canvas?.drawOval(RectF(
            (maxWidthPixels/4 + maxWidthPixels/40).toFloat(),
            (maxHeightPixel/2 - maxWidthPixels/4 + maxWidthPixels/40).toFloat(),
            (maxWidthPixels - maxWidthPixels/4 - maxWidthPixels/40).toFloat(),
            (maxHeightPixel/2 + maxWidthPixels/4 - maxWidthPixels/40).toFloat()
        ),
            paintOval)

    }


    private fun checkTimeInput(timeName: String, newValue: Int ): Int{
        when (timeName){
            NAME_HOUR -> {
                return if (newValue in 0..11){
                    newValue
                } else {
                    ERROR_TIME_VALUE
                }
            }
            else -> {
                return if (newValue in 0..59){
                    newValue
                } else {
                    ERROR_TIME_VALUE
                }
            }
        }
    }

    private fun catchTimeInputException(timeName: String, newValue: Int){
        when (timeName){
            NAME_HOUR -> {
                throw java.lang.IndexOutOfBoundsException(
                    "$timeName input parameter must be in 0..11. Your parameter: $newValue"
                )
            }
            else -> {
                throw java.lang.IndexOutOfBoundsException(
                    "$timeName input parameter must be in 0..59. Your parameter: $newValue"
                )
            }
        }
    }

}