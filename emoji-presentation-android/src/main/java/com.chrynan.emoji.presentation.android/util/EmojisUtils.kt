@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import androidx.emoji.text.EmojiCompat
import com.chrynan.emoji.core.Emojis

/**
 * Initializes the provided [config] for the application.
 */
fun Emojis.init(config: EmojiCompat.Config): EmojiCompat = EmojiCompat.init(config)
