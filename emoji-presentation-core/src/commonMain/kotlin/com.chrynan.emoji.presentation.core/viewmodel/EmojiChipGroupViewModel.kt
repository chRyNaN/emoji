package com.chrynan.emoji.presentation.core.viewmodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmojiChipGroupViewModel(
    @SerialName(value = "chips") val chips: Set<EmojiChipViewModel> = emptySet()
)
