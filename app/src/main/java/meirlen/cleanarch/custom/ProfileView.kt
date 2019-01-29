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
import android.support.v7.widget.AppCompatImageView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.bumptech.glide.Glide


class ProfileView(context: Context, attrs: AttributeSet? = null) : AppCompatImageView(context, attrs) {

    private val highCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val middleCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val bottomCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    private val bottomX = 250F
    private val bottomY = 400F
    private val bottomR = 250F


    private lateinit var mDrawable: Drawable
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mImageResource: Int = 0

    init {
        highCirclePaint.color = ContextCompat.getColor(context, R.color.highCircleColor)
        middleCirclePaint.color = ContextCompat.getColor(context, R.color.middleCircleColor)
        bottomCirclePaint.color = ContextCompat.getColor(context, R.color.bottomCircleColor)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImage, 0, 0)


        try {
            mImageResource = typedArray.getInteger(R.styleable.CircleImage_src, R.drawable.ava_picture)
        } finally {
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        // canvas.drawCircle(bottomX, bottomY, bottomR * 2, highCirclePaint)
        // canvas.drawCircle(bottomX, bottomY, bottomR + (bottomR / 2), middleCirclePaint)
        // canvas.drawCircle(bottomX, bottomY, bottomR, bottomCirclePaint)
        showImage()
    }

    /**
     * create placeholder drawable
     */
    private fun createDrawable() {
        mDrawable = object : Drawable() {
            override fun draw(canvas: Canvas) {
                val centerX = Math.round(canvas.width * 0.5f)
                val centerY = Math.round(canvas.height * 0.5f)

                /**
                 * draw a circle shape for placeholder image
                 */
                canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), (canvas.height / 2).toFloat(), mPaint)
                canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), (canvas.height / 2).toFloat(), mBorderPaint)
            }

            override fun setAlpha(i: Int) {

            }

            override fun setColorFilter(colorFilter: ColorFilter?) {

            }

            override fun getOpacity(): Int {
                return PixelFormat.UNKNOWN
            }
        }
    }

    private fun fillImages() {
        createDrawable()
        Glide.with(context)
            .load(mImageResource)
            .placeholder(mDrawable)
            .centerCrop()
            .override(100, 100)
            .into(this)
    }

    fun showImage() {
        fillImages()
    }
}
