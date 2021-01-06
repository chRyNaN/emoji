@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.chrynan.emoji.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an emoji and all of it's related data.
 *
 * @property [unicodeString] The [String] of unicode values that represent this emoji (ex: U+1F600). Note that each
 * unicode value should be prefaced with the "U+" String.
 * @property [unicodeList] The [List] of unicode [String] values that represent this emoji (ex: listOf(U+1F600)). This
 * is different from the [unicodeString] value, as that includes all of the unicode Strings in a single [String] value,
 * whereas, this [unicodeList] contains a list of all of those unicode Strings. Note that each unicode value should be
 * prefaced with the "U+" String.
 * @property [char] The actual emoji character representation of this emoji (ex: 😀). Note that this is not a Kotlin
 * [Char] value but is actually a [String].
 * @property [name] The name of this emoji.
 * @property [aliases] The alias name of this emoji (ex: :name:). This is useful for applications that show emoji results
 * to the User as they type beginning with a certain character (usually ':').
 * @property [category] The name of the category this emoji belongs to. This is useful for partitioning emojis.
 * @property [group] The name of the group this emoji belongs to within the [category]. This is useful for fine grained
 * partitioning of emojis.
 * @property [iconUri] An optional URI to an image representation of this emoji.
 */
@Serializable
data class Emoji(
    @SerialName(value = "unicode") val unicodeString: String,
    @SerialName(value = "char") val char: String,
    @SerialName(value = "name") val name: String,
    @SerialName(value = "alias") val aliases: List<String> = emptyList(),
    @SerialName(value = "category") val category: String? = null,
    @SerialName(value = "group") val group: String? = null,
    @SerialName(value = "icon") val iconUri: String? = null
) {

    /**
     * Retrieves the [List] of unicode [String] values that represent this emoji (ex: listOf(U+1F600)). This
     * is different from the [unicodeString] value, as that includes all of the unicode Strings in a single [String] value,
     * whereas, this [unicodeList] contains a list of all of those unicode Strings. Note that each unicode value should be
     * prefaced with the "U+" String.
     *
     * Note that this is derived data from the [unicodeString] value and lazily initialized.
     */
    val unicodeList: List<String> by lazy { unicodeString.split("U+", " ", ignoreCase = true) }

    companion object {

        const val DEFAULT_LOOKUP_CHAR = ':'
    }
}
