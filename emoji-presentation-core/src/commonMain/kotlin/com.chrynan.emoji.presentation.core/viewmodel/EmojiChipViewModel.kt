package com.chrynan.emoji.presentation.core.viewmodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmojiChipViewModel(
    @SerialName(value = "emoji") val emojiViewModel: EmojiViewModel,
    @SerialName(value = "count") val count: Long? = null,
    @SerialName(value = "is_selected") val isSelected: Boolean = false
)
