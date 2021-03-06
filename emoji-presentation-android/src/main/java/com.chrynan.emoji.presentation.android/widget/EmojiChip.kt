@file:Suppress("unused", "FunctionName")

package com.chrynan.emoji.presentation.android.widget

import android.content.Context
import android.util.AttributeSet
import com.chrynan.emoji.presentation.android.util.AndroidNumberFormatter
import com.chrynan.emoji.presentation.android.util.toCharSequence
import com.chrynan.emoji.presentation.core.viewmodel.EmojiChipViewModel
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

    private val numberFormatter = AndroidNumberFormatter()

    var emojiChipViewModel: EmojiChipViewModel? = null
        set(value) {
            field = value

            val emojiText = emojiChipViewModel?.emojiViewModel?.toCharSequence(
                context = context,
                onBuildStart = { stringBuilder ->
                    emojiChipViewModel?.count?.let {
                        val formattedCount = numberFormatter.format(it)
                        stringBuilder.append("$formattedCount ")
                    }
                },
                onImageLoadError = { _, _ -> invalidate() },
                onImageLoadSuccess = { _, _ -> invalidate() }
            )

            this.text = emojiText
            this.isChecked = value?.isSelected ?: false
        }
}

/**
 * A convenience function for creating an [EmojiChip] for the provided [emojiChipViewModel]. This
 * is shorthand for creating an [EmojiChip] and assigning the values to its
 * [EmojiChip.emojiChipViewModel] property.
 */
fun EmojiChip(
    emojiChipViewModel: EmojiChipViewModel,
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): EmojiChip {
    val chip = EmojiChip(context = context, attrs = attrs, defStyleAttr = defStyleAttr)
    chip.emojiChipViewModel = emojiChipViewModel
    return chip
}
