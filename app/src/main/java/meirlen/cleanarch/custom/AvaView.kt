package meirlen.cleanarch.custom

import android.content.Context
import android.graphics.*
import android.graphics.Color.BLACK
import android.support.v4.content.ContextCompat
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.layout_lce.view.*
import meirlen.cleanarch.R
import meirlen.cleanarch.utill.ext.loadImage
import android.graphics.drawable.Drawable
import android.support.v7.widget.AppCompatImageView
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.bumptech.glide.Glide


class AvaView(context: Context, attrs: AttributeSet? = null) : AppCompatImageView(context, attrs) {

    private val highCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val middleCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val bottomCirclePaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val mClipPath = Path()
    private var mImageSize: Int = 0
    private val mRect = RectF()
    private var mCirleX = 0f
    private var mCirleY = 0f
    private var mMarginStart = 0f


    init {
        middleCirclePaint.color = ContextCompat.getColor(context, R.color.middleCircleColor)
        bottomCirclePaint.color = ContextCompat.getColor(context, R.color.bottomCircleColor)
        highCirclePaint.color = ContextCompat.getColor(context, R.color.highCircleColor)
        mImageSize = getContext().resources.getDimensionPixelSize(R.dimen.dp_300)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val screenWidth = View.MeasureSpec.getSize(widthMeasureSpec)
        val screenHeight = View.MeasureSpec.getSize(heightMeasureSpec)
        mRect.set(0F, 0F, screenWidth.toFloat(), screenHeight.toFloat())
        mMarginStart = screenWidth / 6f
        setMeasuredDimension(screenWidth - mMarginStart.toInt(), screenHeight)
    }

    override fun onDraw(canvas: Canvas) {
        mCirleX = mRect.centerX() - mMarginStart
        mCirleY = mRect.centerY() - mMarginStart
        canvas.drawCircle(mCirleX, mCirleY, mRect.height() / 2, highCirclePaint)
        canvas.drawCircle(mCirleX, mCirleY, (mRect.height() / 3) + ((mRect.height() / 5) / 2.5f), middleCirclePaint)
        canvas.drawCircle(mCirleX, mCirleY, mRect.height() / 3f, bottomCirclePaint)
        mClipPath.addCircle(mCirleX, mCirleY, mRect.height() / 3.75f, Path.Direction.CW)
        canvas.clipPath(mClipPath)
        super.onDraw(canvas)
    }

    fun showImage(url: String) {
        loadImage(url, mImageSize, mImageSize)
    }


}
