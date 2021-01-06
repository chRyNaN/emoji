package com.chrynan.emoji.presentation.core

import com.chrynan.emoji.core.Emoji

data class EmojiViewModel(
    val emoji: Emoji,
    val isIconPreferred: Boolean = false,
    val isStaticUriPreferred: Boolean = false
)
