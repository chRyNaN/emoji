package com.chrynan.emoji.presentation.android.viewmodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmojiChipViewModel(
    @SerialName(value = "emoji") val emojiViewModel: EmojiViewModel,
    @SerialName(value = "count") val count: Long? = null,
    @SerialName(value = "is_selected") val isSelected: Boolean = false
) {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false

        if (other !is EmojiChipViewModel) return false

        return emojiViewModel == other.emojiViewModel
    }

    override fun hashCode(): Int = emojiViewModel.hashCode()
}
