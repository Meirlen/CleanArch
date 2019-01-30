package meirlen.cleanarch.utill.ext

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 *  A method to load image (Default)
 *  @see com.bumptech.glide.Glide
 */
internal fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .asBitmap()
        .centerCrop()
        .into(this)
}

/**
 * @param url
 * @param width
 * @param height
 *
 */
internal fun ImageView.loadImage(url: String, width: Int, height: Int) {
    Glide.with(this.context)
        .load(url)
        .asBitmap()
        .override(width,height)
        .into(this)
}