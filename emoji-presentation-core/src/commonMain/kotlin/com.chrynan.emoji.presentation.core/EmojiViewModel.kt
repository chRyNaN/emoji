@file:Suppress("unused")

package com.chrynan.emoji.presentation.core

import com.chrynan.emoji.core.Emoji

/**
 * A View Model representation of an [Emoji].
 *
 * @property [emoji] The [Emoji] this View Model represents.
 * @property [isIconPreferred] A [Boolean] indicating whether the [Emoji.Unicode.iconUri] is preferred if it is
 * available.
 * @property [isStaticUriPreferred] A [Boolean] indicating whether the [Emoji.Custom.staticUri] is preferred if it is
 * available.
 */
data class EmojiViewModel(
    val emoji: Emoji,
    val isIconPreferred: Boolean = false,
    val isStaticUriPreferred: Boolean = false
)

/**
 * A convenience function for converting this [Emoji] into an [EmojiViewModel].
 */
fun Emoji.toViewModel(isIconPreferred: Boolean = false, isStaticUriPreferred: Boolean = false): EmojiViewModel =
    EmojiViewModel(emoji = this, isIconPreferred = isIconPreferred, isStaticUriPreferred = isStaticUriPreferred)
