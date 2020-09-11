package com.chrynan.emoji.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a group, or collection, of emojis.
 *
 * @property [name] The name of this group.
 * @property [emojis] The list of [Emoji]s within this group.
 * @property [icon] An optional [Emoji] acting as an icon for this group.
 */
@Serializable
data class EmojiGroup(
    @SerialName(value = "name") val name: String,
    @SerialName(value = "emojis") val emojis: List<Emoji>,
    @SerialName(value = "icon") val icon: Emoji? = null
)