@file:Suppress("unused")

package com.chrynan.emoji.presentation.android

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.presentation.core.EmojiViewModel

/**
 * An Android [View] that can display an [EmojiViewModel].
 */
class EmojiWidget : FrameLayout {

    var emojiViewModel: EmojiViewModel? = null
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

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                tooltipText = emoji?.name
            }
        }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.widget_emoji, this)

        emojiImageView.visibility = View.INVISIBLE
        emojiTextView.visibility = View.INVISIBLE
    }

    @Suppress("MemberVisibilityCanBePrivate")
    val emojiImageView: ImageView by lazy { findViewById(R.id.emojiWidgetImageView) }

    @Suppress("MemberVisibilityCanBePrivate")
    val emojiTextView: TextView by lazy { findViewById(R.id.emojiWidgetTextView) }
}

/**
 * A convenience function for creating an [EmojiWidget] for the provided [viewModel]. This is shorthand for creating an
 * [EmojiWidget] and assigning the [viewModel] to its [EmojiWidget.emojiViewModel] property.
 */
fun EmojiWidget(
    context: Context,
    viewModel: EmojiViewModel,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): EmojiWidget {
    val widget = EmojiWidget(context = context, attrs = attrs, defStyleAttr = defStyleAttr)
    widget.emojiViewModel = viewModel
    return widget
}
