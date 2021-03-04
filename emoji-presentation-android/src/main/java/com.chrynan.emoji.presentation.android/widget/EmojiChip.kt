@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.widget

import android.content.Context
import android.util.AttributeSet
import com.chrynan.emoji.presentation.android.util.toCharSequence
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import com.google.android.material.chip.Chip

/**
 * A [Chip] that displays an [EmojiViewModel] and an optional count value.
 */
class EmojiChip @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Chip(context, attrs, defStyleAttr) {

    fun setEmoji(emojiViewModel: EmojiViewModel, count: Long? = null) {
        val emojiText = emojiViewModel.toCharSequence(
            context = context,
            onBuildStart = {
                if (count != null) it.append("$count ")
            },
            onImageLoadError = { _, _ -> invalidate() },
            onImageLoadSuccess = { _, _ -> invalidate() })

        this.text = emojiText
    }
}

/**
 * A convenience function for creating an [EmojiChip] for the provided [emojiViewModel] and
 * optional [count]. This is shorthand for creating an [EmojiChip] and assigning the values to its
 * [EmojiChip.setEmoji] function.
 */
fun EmojiChip(
    emojiViewModel: EmojiViewModel,
    count: Long? = null,
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): EmojiChip {
    val chip = EmojiChip(context = context, attrs = attrs, defStyleAttr = defStyleAttr)
    chip.setEmoji(emojiViewModel = emojiViewModel, count = count)
    return chip
}
