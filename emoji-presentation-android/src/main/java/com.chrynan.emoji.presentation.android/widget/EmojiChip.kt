@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.widget

import android.content.Context
import android.util.AttributeSet
import com.chrynan.emoji.presentation.android.util.toCharSequence
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import com.google.android.material.chip.Chip

class EmojiChip(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : Chip(context, attrs, defStyleAttr) {

    fun setEmoji(viewModel: EmojiViewModel, count: Long? = null) {
        val emojiText = viewModel.toCharSequence(
            context = context,
            onBuildStart = {
                if (count != null) it.append("$count ")
            },
            onImageLoadError = { _, _ -> invalidate() },
            onImageLoadSuccess = { _, _ -> invalidate() })

        this.text = emojiText
    }
}
