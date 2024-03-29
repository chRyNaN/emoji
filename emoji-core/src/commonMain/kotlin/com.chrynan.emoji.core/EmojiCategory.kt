@file:Suppress("unused")

package com.chrynan.emoji.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a category, or collection of [EmojiGroup]s.
 *
 * @property [name] The name of this category.
 * @property [groups] The list of [EmojiGroup]s within this category.
 * @property [icon] An optional [Emoji] acting as an icon for this category.
 */
@Serializable
data class EmojiCategory(
    @SerialName(value = "name") val name: String? = null,
    @SerialName(value = "groups") val groups: List<EmojiGroup>,
    @SerialName(value = "icon") val icon: Emoji? = null
)
