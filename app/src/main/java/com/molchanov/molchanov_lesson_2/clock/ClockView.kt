package com.molchanov.molchanov_lesson_2.clock

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.molchanov.molchanov_lesson_2.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin
import kotlin.properties.Delegates

/**
 * Класс реализующий отрисовку аналоговых часов
 */
class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.ClockViewStyle
) : View(context, attrs, defStyleAttr), IClockView {

    private var isInit = false
    private var exactlyMinimum = 0

    companion object {
        const val ARROW_HOUR_COLOR = Color.BLACK
        const val ARROW_MINUTE_COLOR = Color.BLACK
        const val ARROW_SECOND_COLOR = Color.RED

        const val NAME_HOUR = "Hour"
        const val NAME_MINUTE = "Minute"
        const val NAME_SECOND = "Second"
    }

    //region инициализация рабочих параметров

    //Минимальный размер часов
    private val minMeasure = min(
        resources.displayMetrics.widthPixels,
        resources.displayMetrics.heightPixels
    ) / 4

    //Переменные для поздней реализации_____________________________
    //Ширина и высота экрана в пикселях
    private var maxWidthPixels by Delegates.notNull<Int>()
    private var maxHeightPixel by Delegates.notNull<Int>()

    private var centerWidthPixel by Delegates.notNull<Float>()
    private var centerHeightPixel by Delegates.notNull<Float>()

    //Длины (циферблата и стрелок)
    private var radiusSizePixels by Delegates.notNull<Float>()
    private var secondArrowSizePixels by Delegates.notNull<Float>()
    private var minuteArrowSizePixels by Delegates.notNull<Float>()
    private var hourArrowSizePixels by Delegates.notNull<Float>()

    //Массив координат для отрисовки стрелок
    private lateinit var linesArray: FloatArray

    //Наклон линий рисок
    private lateinit var timeDegrees: FloatArray
    private lateinit var hourArrayCoordinates: FloatArray
    private lateinit var minuteArrayCoordinates: FloatArray
    private lateinit var secondArrayCoordinates: FloatArray
    //_____________________________________________________________

    private val paintBrash = Paint(Paint.ANTI_ALIAS_FLAG)
    private val clockFaceLieWidth = resources.displayMetrics.density * 4

    //Цвета стрелок
    private var arrowHourColor by Delegates.notNull<Int>()
    private var arrowMinuteColor by Delegates.notNull<Int>()
    private var arrowSecondColor by Delegates.notNull<Int>()

    //Текущие значения времени
    private var hourValue = 0
    private var minuteValue = 0
    private var secondValue = 0

    init {
        if (attrs == null) initDefaultAttributes()
        else initAttributes(attrs, defStyleAttr)
    }

    //Инициализация входных аттрибутов
    private fun initAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val attrArray: TypedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.ClockView,
            defStyleAttr,
            R.style.DefaultClockViewStyle
        )

        arrowHourColor =
            attrArray.getColor(R.styleable.ClockView_arrow_hour_color, ARROW_HOUR_COLOR)
        arrowMinuteColor =
            attrArray.getColor(R.styleable.ClockView_arrow_minute_color, ARROW_MINUTE_COLOR)
        arrowSecondColor =
            attrArray.getColor(R.styleable.ClockView_arrow_second_color, ARROW_SECOND_COLOR)

        attrArray.recycle()
    }

    //Инициализация цвета стрелок
    private fun initDefaultAttributes() {
        arrowHourColor = ARROW_HOUR_COLOR
        arrowMinuteColor = ARROW_MINUTE_COLOR
        arrowSecondColor = ARROW_SECOND_COLOR
    }
    //endregion

    //region функции взаимодействия с элементами часов
    /**
     * Функции установки цвета для стрелок
     */
    override fun setHourColorsForArrows(hourColor: Int) {
        arrowHourColor = hourColor
    }

    override fun setMinuteColorsForArrows(minuteColor: Int) {
        arrowMinuteColor = minuteColor
    }

    override fun setSecondColorsForArrows(secondColor: Int) {
        arrowSecondColor = secondColor
    }

    /**
     * Функция для задания времени
     */
    override fun setTime(hour: Int, minute: Int, second: Int) {
        if (isInit) {
            try {
                checkTimeInput(NAME_HOUR, hour)
                checkTimeInput(NAME_MINUTE, minute)
                checkTimeInput(NAME_SECOND, second)
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()

                //TODO Индикация: время отображается не корректно
            }

            hourValue = hour
            minuteValue = minute
            secondValue = second

            timeDegrees = getDegreeFromTimeValue()

            hourArrayCoordinates = getCoordinatesFromDegree(
                centerWidthPixel, centerHeightPixel,
                hourArrowSizePixels,
                floatArrayOf(timeDegrees[0])
            )

            minuteArrayCoordinates = getCoordinatesFromDegree(
                centerWidthPixel, centerHeightPixel,
                minuteArrowSizePixels,
                floatArrayOf(timeDegrees[1])
            )

            secondArrayCoordinates = getCoordinatesFromDegree(
                centerWidthPixel, centerHeightPixel,
                secondArrowSizePixels,
                floatArrayOf(timeDegrees[2])
            )

            invalidate()
        }
    }
    //endregion

    //Функция задания размеров
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val minWidth = minMeasure + paddingLeft + paddingRight
        val minHeight = minMeasure + paddingBottom + paddingTop

        exactlyMinimum =
            min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))

        setMeasuredDimension(
            resolveSizeAndState(minWidth, widthMeasureSpec),
            resolveSizeAndState(minHeight, heightMeasureSpec)
        )
    }

    /**
     * size - рассчитанное
     * specSize - заданное в View
     */
    private fun resolveSizeAndState(size: Int, measureSpec: Int): Int {

        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        val result: Int = when (specMode) {
            MeasureSpec.AT_MOST -> {
                maxWidthPixels = size
                maxHeightPixel = size
                size
            }
            MeasureSpec.EXACTLY -> {
                if (specSize < size) {
                    maxWidthPixels = size
                    maxHeightPixel = size
                    size
                } else {
                    maxWidthPixels = exactlyMinimum
                    maxHeightPixel = exactlyMinimum
                    specSize
                }
            }
            MeasureSpec.UNSPECIFIED -> {
                maxWidthPixels = size
                maxHeightPixel = size
                size
            }
            else -> {
                maxWidthPixels = size
                maxHeightPixel = size
                size
            }
        }
        return result
    }

    //Бизнес-логика отрисовки
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (isInit) {

            paintWatchFace(canvas)

            try {
                paintTimeArrows(canvas)
            } catch (e: ArrayIndexOutOfBoundsException) {
                e.printStackTrace()

                //TODO Индикация: часы работают не корректно
            }
        } else {
            initAllValues()

            isInit = true

            requestLayout()
        }
    }

    /**
     * Функция отрисовывает циферблат
     */
    private fun paintWatchFace(canvas: Canvas?) {

        //Отрисовка пустотелой окружности
        paintBrash.let {
            it.color = Color.BLACK
            it.style = Paint.Style.STROKE
            it.strokeWidth = clockFaceLieWidth
        }

        canvas?.drawOval(
            RectF(
                maxHeightPixel / 2 - radiusSizePixels,
                maxHeightPixel / 2 - radiusSizePixels,
                maxWidthPixels / 2 + radiusSizePixels,
                maxHeightPixel / 2 + radiusSizePixels
            ),
            paintBrash
        )

        //Отрисовка линий от центра к краям
        canvas?.drawLines(
            linesArray, paintBrash
        )

        //Отрисовка вложенного круга
        paintBrash.let {
            it.color = Color.WHITE
            it.style = Paint.Style.FILL
        }

        canvas?.drawOval(
            RectF(
                maxHeightPixel / 2 - radiusSizePixels * 0.9f,
                maxHeightPixel / 2 - radiusSizePixels * 0.9f,
                maxWidthPixels / 2 + radiusSizePixels * 0.9f,
                maxHeightPixel / 2 + radiusSizePixels * 0.9f
            ),
            paintBrash
        )
    }

    /**
     * Функция отрисовки стрелок на циферблате
     */
    @Throws(ArrayIndexOutOfBoundsException::class)
    private fun paintTimeArrows(canvas: Canvas?) {
        //отрисовывка часовой стрелки
        paintBrash.let {
            it.color = arrowHourColor
            it.style = Paint.Style.STROKE
            it.strokeWidth = resources.displayMetrics.density * 8
        }

        canvas?.drawLines(
            hourArrayCoordinates,
            paintBrash
        )

        //отрисовывка минутной стрелки
        paintBrash.let {
            it.color = arrowMinuteColor
            it.strokeWidth = resources.displayMetrics.density * 4
        }

        canvas?.drawLines(
            minuteArrayCoordinates,
            paintBrash
        )

        //отрисовывка секундной стрелки
        paintBrash.let {
            it.color = arrowSecondColor
            it.strokeWidth = resources.displayMetrics.density * 2
        }

        canvas?.drawLines(
            secondArrayCoordinates,
            paintBrash
        )
    }

    /**
     * Функция проверяет корректность введённых параметров времени
     */
    @Throws(IndexOutOfBoundsException::class)
    private fun checkTimeInput(timeName: String, newValue: Int): Int {
        when (timeName) {
            NAME_HOUR -> {
                if (newValue in 0..11) {
                    return newValue
                } else {
                    throw java.lang.IndexOutOfBoundsException(
                        "Class ${this::class.simpleName}: input parameter \"$timeName\" must be in 0..11. Your parameter: $newValue"
                    )
                }
            }
            else -> {
                if (newValue in 0..59) {
                    return newValue
                } else {
                    throw java.lang.IndexOutOfBoundsException(
                        "Class ${this::class.simpleName}: input parameter \"$timeName\" must be in 0..59. Your parameter: $newValue"
                    )
                }
            }
        }
    }

    /**
     * Функция переводит значения времени в углы поворота стрелки (в градусах)
     */
    private fun getDegreeFromTimeValue(): FloatArray {
        val hour = hourValue.toFloat()
        val minute = minuteValue.toFloat()
        val second = secondValue.toFloat()

        return floatArrayOf(
            -1 * (180 + hour * 30 + minute / 2),
            -1 * (180 + minute * 6 + second / 10),
            -1 * (180 + second * 6)
        )
    }

    /**
     * Функция принимает параметры:
     * @param startX - начальная координата X в пикселях
     * @param startY - начальная координата X в пикселях
     * @param length - длинну линии
     * @param degrees - массив градусов для поворота относительно начальной точки
     * Возвращает сплошной масиив с координатами линии в формате
     * (X начало, Y начало, X конец, Y конец)
     */
    private fun getCoordinatesFromDegree(
        startX: Float, startY: Float, length: Float, degrees: FloatArray
    ): FloatArray {

        val res = mutableListOf<Float>()

        degrees.forEach {
            res.add(startX)
            res.add(startY)
            res.add(centerWidthPixel + (length * (sin(it * 0.0174533F))))
            res.add(centerHeightPixel + (length * (cos(it * 0.0174533F))))
        }

        return res.toFloatArray()
    }

    /**
     * Инициализация всех параметров участвующих в отрисовке часов
     */
    private fun initAllValues() {

        centerWidthPixel = (maxWidthPixels / 2).toFloat()
        centerHeightPixel = (maxHeightPixel / 2).toFloat()

        //Длины (циферблата и стрелок)
        radiusSizePixels = (maxWidthPixels).toFloat() / 2 - clockFaceLieWidth

        secondArrowSizePixels = radiusSizePixels * 0.85F
        minuteArrowSizePixels = radiusSizePixels * 0.65F
        hourArrowSizePixels = radiusSizePixels * 0.45F

        //Массив координат для отрисовки стрелок
        linesArray = getCoordinatesFromDegree(
            centerWidthPixel, centerHeightPixel,
            radiusSizePixels,
            floatArrayOf(0F, 30F, 60F, 90F, 120F, 150F, 180F, 210F, 240F, 270F, 300F, 330F)
        )

        //Наклон линий рисок
        timeDegrees = getDegreeFromTimeValue()

        hourArrayCoordinates = getCoordinatesFromDegree(
            centerWidthPixel, centerHeightPixel,
            hourArrowSizePixels,
            floatArrayOf(timeDegrees[0])
        )

        minuteArrayCoordinates = getCoordinatesFromDegree(
            centerWidthPixel, centerHeightPixel,
            minuteArrowSizePixels,
            floatArrayOf(timeDegrees[1])
        )

        secondArrayCoordinates = getCoordinatesFromDegree(
            centerWidthPixel, centerHeightPixel,
            secondArrowSizePixels,
            floatArrayOf(timeDegrees[2])
        )
    }
}