@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.viewmodel

import com.chrynan.emoji.core.Emoji
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A View Model representation of an [Emoji].
 *
 * @property [emoji] The [Emoji] this View Model represents.
 * @property [isIconPreferred] A [Boolean] indicating whether the [Emoji.Unicode.iconUri] is preferred if it is
 * available.
 * @property [isStaticUriPreferred] A [Boolean] indicating whether the [Emoji.Custom.staticUri] is preferred if it is
 * available.
 */
@Serializable
data class EmojiViewModel(
    @SerialName(value = "emoji") val emoji: Emoji,
    @SerialName(value = "is_icon_preferred") val isIconPreferred: Boolean = false,
    @SerialName(value = "is_static_uri_preferred") val isStaticUriPreferred: Boolean = false
) : ListItemViewModel {

    override val uniqueId: Long = hashCode().toLong()
}

/**
 * A convenience function for converting this [Emoji] into an [EmojiViewModel].
 */
fun Emoji.toViewModel(
    isIconPreferred: Boolean = false,
    isStaticUriPreferred: Boolean = false
): EmojiViewModel =
    EmojiViewModel(
        emoji = this,
        isIconPreferred = isIconPreferred,
        isStaticUriPreferred = isStaticUriPreferred
    )
