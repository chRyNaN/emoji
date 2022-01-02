package com.chrynan.emoji.presentation.android.viewmodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmojiChipGroupViewModel(
    @SerialName(value = "chips") val chips: Set<EmojiChipViewModel> = emptySet()
)
