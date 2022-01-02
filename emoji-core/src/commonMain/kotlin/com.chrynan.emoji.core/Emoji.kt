@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package com.chrynan.emoji.core

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

/**
 * A sealed class that represents an Emoji. An implementation of this [Emoji] class should be able
 * to be rendered to the UI to show the User a visual representation of the Emoji. Implementations
 * of this [Emoji] class include [Emoji.Unicode], which represents a standard Emoji unicode
 * sequence, and [Emoji.Custom], which represents a custom Emoji defined by a URI value.
 *
 * @property [typeName] An identifier value that distinguishes between the different [Emoji]
 * implementation types. For example, a value of [Emoji.Unicode.TYPE_NAME] means the the [Emoji]
 * should be an instance of [Emoji.Unicode].
 *
 * @property [name] The name used to identify this particular [Emoji]. This value should be without
 * the lookup character prefix and suffix (:name:), and should just contain the name [String]
 * value. However, there are ways for this property to be used correctly even if the lookup
 * character is included in the prefix and suffix.
 *
 * @property [aliases] Extra names used to identify this particular [Emoji]. This provides a way
 * for multiple and alternate names to be used for a single [Emoji].
 *
 * @property [category] The optional name of the category this emoji belongs to. This is useful for
 * partitioning [Emoji]s.
 *
 * @property [group] The optional name of the group this emoji belongs to within the [category].
 * This is useful for fine grained partitioning of [Emoji]s.
 *
 * @property [key] A unique key identifier for this [Emoji]. This value could be used to uniquely
 * identify [Emoji]s within a [Collection].
 *
 * @property [variant] The name of the variant of the core [Emoji]. This is useful when there are
 * multiple of the same [Emoji] but with slight deviations. For instance, some hand gesture
 * [Emoji]s have variants for different skin tones. They are the same [Emoji] but with slight
 * deviations. Note that not all [Emoji]s have variants, and as such, this property is optional.
 */
@Serializable
sealed class Emoji {

    @SerialName(value = "type")
    abstract val typeName: String
    abstract val name: String
    abstract val aliases: List<String>
    abstract val category: String?
    abstract val group: String?
    abstract val key: Key
    abstract val variant: String?

    /**
     * Represents a Unicode [Emoji] and all of it's related data.
     *
     * @property [unicodeString] The [String] of unicode values that represent this emoji
     * (ex: U+1F600). Note that each unicode value should be prefaced with the "U+" String.
     *
     * @property [unicodeList] The [List] of unicode [String] values that represent this emoji
     * (ex: listOf(U+1F600)). This is different from the [unicodeString] value, as that includes
     * all the unicode Strings in a single [String] value, whereas, this [unicodeList] contains
     * a list of all of those unicode Strings. Note that each unicode value should be prefaced with
     * the "U+" String.
     *
     * @property [char] The actual emoji character representation of this emoji (ex: 😀). Note that
     * this is not a Kotlin [Char] value but is actually a [String].
     *
     * @property [iconUri] An optional URI to an image representation of this emoji.
     */
    @Serializable
    @OptIn(ExperimentalSerializationApi::class)
    data class Unicode internal constructor(
        @SerialName(value = "name") override val name: String,
        @SerialName(value = "aliases") override val aliases: List<String> = emptyList(),
        @SerialName(value = "category") override val category: String? = null,
        @SerialName(value = "group") override val group: String? = null,
        @SerialName(value = "variant") override val variant: String? = null,
        @SerialName(value = "icon") val iconUri: String? = null,
        @SerialName(value = "unicode") val unicodeString: String,
        @SerialName(value = "unicode_list") val unicodeList: List<String>,
        @SerialName(value = "char") @JsonNames("character") val character: String
    ) : Emoji() {

        @SerialName(value = "type")
        override val typeName: String = "unicode"

        override val key: Key = Key(
            typeName = typeName,
            name = name,
            id = unicodeString
        )
    }

    /**
     * Represents a Custom [Emoji] and all of it's related data. A Custom [Emoji] does not have a
     * unicode value or character representing the [Emoji]. Instead it is represented by a [uri]
     * value that points to a resource that illustrates the [Emoji].
     *
     * @property [uri] The URI value to the image resource visually representing this [Emoji].
     *
     * @property [staticUri] An optional URI value to a static version of the image resource
     * visually representing this [Emoji]. This is useful as an alternative and regular image
     * resource if the [uri] value is an animated GIF.
     *
     * @property [mimeType] The optional mime type of the image located at the [uri] value.
     */
    @Serializable
    data class Custom internal constructor(
        @SerialName(value = "name") override val name: String,
        @SerialName(value = "aliases") override val aliases: List<String> = emptyList(),
        @SerialName(value = "category") override val category: String? = null,
        @SerialName(value = "group") override val group: String? = null,
        @SerialName(value = "variant") override val variant: String? = null,
        @SerialName(value = "uri") val uri: String,
        @SerialName(value = "static_uri") val staticUri: String? = null,
        @SerialName(value = "mime_type") val mimeType: String? = null
    ) : Emoji() {

        @SerialName(value = "type")
        override val typeName: String = "custom"

        override val key: Key = Key(
            typeName = typeName,
            name = name,
            id = uri
        )
    }

    /**
     * Represents a key identifier for an [Emoji]. The [typeName], [name], [id], and [variant]
     * values should be enough to uniquely identify an [Emoji] within a collection of values.
     *
     * @property [typeName] The [Emoji.typeName] value of the [Emoji] that this [Key] represents.
     * This value is important to uniquely identify the [Emoji] because different [Emoji]
     * implementations will have different [id] implementations and this provides a way to
     * differentiate between them.
     *
     * @property [name] The [Emoji.name] value of the [Emoji] that this [Key] represents. This
     * value is important to uniquely identify the [Emoji] because some implementations may use the
     * same resource for different [Emoji]s but they still have different names.
     *
     * @property [id] The unique identifier value for this [Emoji]. Note that different [Emoji]
     * implementations may provide different [id] value implementations. This is why the [typeName]
     * property is important to include, so that the [id] values can be differentiated.
     *
     * @property [variant] The [Emoji.variant] value of the [Emoji] that this [Key] represents.
     * This value is important to uniquely identify the [Emoji] because some [Emoji]s have multiple
     * variants with the same name.
     */
    @Serializable
    data class Key(
        @SerialName(value = "type_name") val typeName: String,
        @SerialName(value = "name") val name: String,
        @SerialName(value = "variant") val variant: String? = null,
        @SerialName(value = "id") val id: String
    ) {

        override fun toString(): String = "$typeName:$name:$id:$variant"

        companion object {

            /**
             * Retrieves a [Key] from the provided [String] [value]. Note that the provided [value]
             * is expected to be in the format "typeName:name:id:variant". Also, note that if there
             * is less than two colon (':') characters, this function will throw an
             * [IllegalArgumentException].
             *
             * Note that the variant part of the formatted [String] [value] is optional. It can
             * either be omitted or a value of "null".
             *
             * @param [value] The [String] value in the format "typeName:name:id:variant".
             *
             * @return The [Key] derived from the specially formatted [String] [value].
             */
            fun fromString(value: String): Key {
                val parts = value.split(':')

                require(parts.size >= 3)

                return Key(
                    typeName = parts[0],
                    name = parts[1],
                    id = parts[2],
                    variant = parts.getOrNull(3)
                        ?.let { if (it.trim().lowercase() == "null" || it.isBlank()) null else it }
                )
            }
        }
    }

    companion object {

        /**
         * The default short code lookup character used. When the User begins typing with a short
         * code lookup character, the application knows to look for an [Emoji] with the name
         * following the short code lookup character.
         */
        const val DEFAULT_SHORTCODE_CHAR = ':'
    }
}

@Suppress("FunctionName")
fun Emoji(
    name: String,
    aliases: List<String> = emptyList(),
    category: String? = null,
    group: String? = null,
    variant: String? = null,
    iconUri: String? = null,
    unicodeString: String,
    character: String
): Emoji.Unicode = Emoji.Unicode(
    name = name,
    aliases = aliases,
    category = category,
    group = group,
    variant = variant,
    iconUri = iconUri,
    unicodeString = unicodeString,
    unicodeList = unicodeString.trim()
        .split(
            "U+",
            " ",
            ",",
            ignoreCase = true
        ).map { it.trim() }
        .filter { it.isNotBlank() }
        .map { if (it.startsWith("U+")) it else "U+$it" },
    character = character
)

@Suppress("FunctionName")
fun Emoji(
    name: String,
    aliases: List<String> = emptyList(),
    category: String? = null,
    group: String? = null,
    variant: String? = null,
    iconUri: String? = null,
    unicodeList: List<String>,
    character: String
): Emoji.Unicode = Emoji.Unicode(
    name = name,
    aliases = aliases,
    category = category,
    group = group,
    variant = variant,
    iconUri = iconUri,
    unicodeString = unicodeList.joinToString(separator = "") { if (it.startsWith("U+")) it else "U+$it" },
    unicodeList = unicodeList,
    character = character
)

@Suppress("FunctionName")
fun Emoji(
    name: String,
    aliases: List<String> = emptyList(),
    category: String? = null,
    group: String? = null,
    variant: String? = null,
    uri: String,
    staticUri: String? = null,
    mimeType: String? = null
): Emoji.Custom = Emoji.Custom(
    name = name,
    aliases = aliases,
    category = category,
    group = group,
    variant = variant,
    uri = uri,
    staticUri = staticUri,
    mimeType = mimeType
)
