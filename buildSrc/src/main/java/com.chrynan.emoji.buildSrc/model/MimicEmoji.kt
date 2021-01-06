package com.chrynan.emoji.buildSrc.model

/**
 * This is a class that represents an Emoji. This class is meant to mimic the com.chrynan.emoji.core.Emoji class but be
 * available during build time of this project. The property names should match the SerialName values in the
 * com.chrynan.emoji.core.Emoji class. This is useful for parsing different formats of Emoji lists and creating a
 * file with values in the format that this library understands natively.
 */
data class MimicEmoji(
    val type: String,
    val unicode: String? = null,
    val char: String? = null,
    val name: String,
    val aliases: List<String> = emptyList(),
    val category: String? = null,
    val group: String? = null,
    val icon: String? = null,
    val uri: String? = null,
    val static_uri: String? = null,
    val mime_type: String? = null
) {

    companion object {

        private const val NAME_TYPE = "type"
        private const val NAME_UNICODE = "unicode"
        private const val NAME_CHAR = "char"
        private const val NAME_NAME = "name"
        private const val NAME_ALIAS = "alias"
        private const val NAME_CATEGORY = "category"
        private const val NAME_GROUP = "group"
        private const val NAME_ICON = "icon"
        private const val NAME_URI = "uri"
        private const val NAME_STATIC_ICON = "static_uri"
        private const val NAME_MIME_TYPE = "mime_type"

        @Suppress("UNCHECKED_CAST")
        fun fromJsonMap(map: Map<String, Any?>): MimicEmoji? {
            val type = (map[NAME_TYPE] as? String?) ?: "unicode"
            val unicode = (map[NAME_UNICODE] as? String?)
            val char = (map[NAME_CHAR] as? String?)
            val name = (map[NAME_NAME] as? String) ?: return null
            val aliasList = (map[NAME_ALIAS] as? List<String>)
            val aliasString = (map[NAME_ALIAS] as? String?)
            val category = (map[NAME_CATEGORY] as? String?)
            val group = (map[NAME_GROUP] as? String?)
            val icon = (map[NAME_ICON] as? String?)
            val uri = (map[NAME_URI] as? String?)
            val staticUri = (map[NAME_STATIC_ICON] as? String?)
            val mimeType = (map[NAME_MIME_TYPE] as? String?)

            return MimicEmoji(
                type = type,
                unicode = unicode,
                char = char,
                name = name,
                aliases = aliasList ?: aliasString?.split(",")?.map { it.trim() } ?: emptyList(),
                category = category,
                group = group,
                icon = icon,
                uri = uri,
                static_uri = staticUri,
                mime_type = mimeType
            )
        }
    }
}
