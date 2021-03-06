@file:Suppress("unused", "FunctionName")

package com.chrynan.emoji.presentation.android.widget

import android.content.Context
import android.util.AttributeSet
import com.chrynan.emoji.core.accessibilityName
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.util.AndroidNumberFormatter
import com.chrynan.emoji.presentation.android.util.setAccessibilityName
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
    defStyleAttr: Int = R.style.Widget_MaterialComponents_Chip_Choice
) : Chip(context, attrs, defStyleAttr) {

    private val numberFormatter = AndroidNumberFormatter()

    var viewModel: EmojiChipViewModel? = null
        set(value) {
            field = value

            val emojiText = viewModel?.emojiViewModel?.toCharSequence(
                context = context,
                onBuildStart = { stringBuilder ->
                    viewModel?.count?.let {
                        val formattedCount = numberFormatter.format(it)
                        stringBuilder.append("$formattedCount ")
                    }
                },
                onImageLoadError = { _, _ -> invalidate() },
                onImageLoadSuccess = { _, _ -> invalidate() }
            )

            this.text = emojiText
            this.isChecked = value?.isSelected ?: false
            this.isActivated = value?.isSelected ?: false
            this.setAccessibilityName(name = value?.emojiViewModel?.emoji?.accessibilityName)
        }
}

/**
 * A convenience function for creating an [EmojiChip] for the provided [viewModel]. This
 * is shorthand for creating an [EmojiChip] and assigning the values to its
 * [EmojiChip.viewModel] property.
 */
fun EmojiChip(
    viewModel: EmojiChipViewModel,
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.Widget_MaterialComponents_Chip_Choice
): EmojiChip {
    val chip = EmojiChip(context = context, attrs = attrs, defStyleAttr = defStyleAttr)
    chip.viewModel = viewModel
    return chip
}
