@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.widget

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.core.accessibilityName
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.util.setAccessibilityName
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import kotlin.math.min

/**
 * An Android [View] that can display an [EmojiViewModel].
 */
class EmojiWidget : FrameLayout {

    var viewModel: EmojiViewModel? = null
        set(value) {
            field = value

            val emoji = value?.emoji

            when (emoji) {
                is Emoji.Unicode -> {
                    if (value.isIconPreferred && emoji.iconUri != null) {
                        emojiImageView.visibility = View.VISIBLE
                        emojiTextView.visibility = View.GONE
                        emojiImageView.load(emoji.iconUri)
                        emojiImageView.contentDescription = emoji.name
                    } else {
                        emojiImageView.visibility = View.GONE
                        emojiTextView.visibility = View.VISIBLE
                        emojiTextView.text = emoji.char
                    }
                }
                is Emoji.Custom -> {
                    emojiImageView.visibility = View.VISIBLE
                    emojiTextView.visibility = View.GONE
                    emojiImageView.contentDescription = emoji.name
                    if (value.isStaticUriPreferred && emoji.staticUri != null) {
                        emojiImageView.load(emoji.staticUri)
                    } else {
                        emojiImageView.load(emoji.uri)
                    }
                }
                else -> {
                    emojiImageView.visibility = View.INVISIBLE
                    emojiTextView.visibility = View.INVISIBLE
                }
            }

            setAccessibilityName(name = emoji?.accessibilityName)
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        LayoutInflater.from(context).inflate(R.layout.widget_emoji, this)

        emojiImageView.visibility = View.INVISIBLE
        emojiTextView.visibility = View.INVISIBLE
    }

    @Suppress("MemberVisibilityCanBePrivate")
    val emojiImageView: ImageView by lazy { findViewById(R.id.emojiWidgetImageView) }

    @Suppress("MemberVisibilityCanBePrivate")
    val emojiTextView: TextView by lazy { findViewById(R.id.emojiWidgetTextView) }

    private var currentTextSizeInPx: Float = 0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        val newTextSizeInPx = min(w, h) / 2f

        if (newTextSizeInPx != currentTextSizeInPx) {
            emojiTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, newTextSizeInPx)
            currentTextSizeInPx = newTextSizeInPx
        }
    }

    private fun pixelsToSp(px: Int): Float = px / resources.displayMetrics.scaledDensity
}

/**
 * A convenience function for creating an [EmojiWidget] for the provided [viewModel]. This is
 * shorthand for creating an [EmojiWidget] and assigning the [viewModel] to its
 * [EmojiWidget.viewModel] property.
 */
fun EmojiWidget(
    viewModel: EmojiViewModel,
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): EmojiWidget {
    val widget = EmojiWidget(context = context, attrs = attrs, defStyleAttr = defStyleAttr)
    widget.viewModel = viewModel
    return widget
}
