package meirlen.cleanarch.custom

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.layout_lce.view.*
import meirlen.cleanarch.R
import meirlen.cleanarch.utill.ext.loadImage
import android.graphics.PixelFormat
import android.graphics.ColorFilter
import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.annotation.Nullable


class ProfileView(context: Context, attrs: AttributeSet? = null) : ImageView(context, attrs) {

    private val highCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val middleCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val bottomCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    private val bottomX = 250F
    private val bottomY = 400F
    private val bottomR = 250F


    private lateinit var mDrawable: Drawable
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        highCirclePaint.color = ContextCompat.getColor(context, R.color.highCircleColor)
        middleCirclePaint.color = ContextCompat.getColor(context, R.color.middleCircleColor)
        bottomCirclePaint.color = ContextCompat.getColor(context, R.color.bottomCircleColor)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(bottomX, bottomY, bottomR * 2, highCirclePaint)
        canvas.drawCircle(bottomX, bottomY, bottomR + (bottomR / 2), middleCirclePaint)
        canvas.drawCircle(bottomX, bottomY, bottomR, bottomCirclePaint)
    }
}
