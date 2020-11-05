package com.ridwanjuanda.news.utils

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.ridwanjuanda.news.R

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

class RectangleLayout : RelativeLayout {
    private var ratio = 0f

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height: Int
        height = if (ratio == -1f) {
            MeasureSpec.getSize(heightMeasureSpec)
        } else {
            Math.round(width / ratio)
        }
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        )
    }

    private fun init(
        context: Context,
        set: AttributeSet?
    ) {
        if (set == null) {
            ratio = 1f
            return
        }
        val ta = context.obtainStyledAttributes(set, R.styleable.RectangleLayout)
        ratio = ta.getFloat(R.styleable.RectangleLayout_custom_ratio, 1f)
        ta.recycle()
    }

    fun setRatio(ratio: Float) {
        this.ratio = ratio
    }
}