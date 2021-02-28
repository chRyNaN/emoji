@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.span

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ReplacementSpan
import coil.target.Target

/**
 * A [ReplacementSpan] that replaces the Text content with an image that is loaded dynamically.
 *
 * Note that when the image is loaded, the TextView will have to be invalidated so that this span
 * can be updated to display the image. This is not handled by this span and should be handled by
 * the image loading logic.
 *
 * Also note that this span class does not perform the image loading logic internally but instead
 * implements the [Target] interface.
 */
class ImageTargetSpan : ReplacementSpan(),
    Target {

    var imageDrawable: Drawable? = null

    override fun getSize(paint: Paint, text: CharSequence, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        if (fm != null) {
            /* update FontMetricsInt or otherwise span does not get drawn when
             * it covers the whole text */
            val metrics = paint.fontMetricsInt
            fm.top = metrics.top
            fm.ascent = metrics.ascent
            fm.descent = metrics.descent
            fm.bottom = metrics.bottom
        }

        return (paint.textSize * 1.2).toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        imageDrawable?.let { drawable ->
            canvas.save()

            val emojiSize = (paint.textSize * 1.1).toInt()
            drawable.setBounds(0, 0, emojiSize, emojiSize)

            var transY = bottom - drawable.bounds.bottom
            transY -= paint.fontMetricsInt.descent / 2

            canvas.translate(x, transY.toFloat())
            drawable.draw(canvas)
            canvas.restore()
        }
    }

    override fun onSuccess(result: Drawable) {
        super.onSuccess(result)

        imageDrawable = result
    }
}
