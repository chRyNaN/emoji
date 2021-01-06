package com.chrynan.emoji.buildSrc.model

/**
 * This is a class that represents an Emoji. This class is meant to mimic the com.chrynan.emoji.core.Emoji class but be
 * available during build time of this project. The property names should match the SerialName values in the
 * com.chrynan.emoji.core.Emoji class. This is useful for parsing different formats of Emoji lists and creating a
 * file with values in the format that this library understands natively.
 */
data class MimicEmoji(
    val unicode: String,
    val char: String,
    val name: String,
    val alias: List<String>,
    val category: String,
    val group: String,
    val icon: String? = null
) {

    companion object {

        private const val NAME_UNICODE = "unicode"
        private const val NAME_CHAR = "char"
        private const val NAME_NAME = "name"
        private const val NAME_ALIAS = "alias"
        private const val NAME_CATEGORY = "category"
        private const val NAME_GROUP = "group"
        private const val NAME_ICON = "icon"

        @Suppress("UNCHECKED_CAST")
        fun fromJsonMap(map: Map<String, Any?>): MimicEmoji? {
            val unicode = (map[NAME_UNICODE] as? String) ?: return null
            val char = (map[NAME_CHAR] as? String) ?: return null
            val name = (map[NAME_NAME] as? String) ?: return null
            val aliasList = (map[NAME_ALIAS] as? List<String>)
            val aliasString = (map[NAME_ALIAS] as? String)
            val category = (map[NAME_CATEGORY] as? String) ?: return null
            val group = (map[NAME_GROUP] as? String) ?: return null
            val icon = (map[NAME_ICON] as? String)

            return MimicEmoji(
                unicode = unicode,
                char = char,
                name = name,
                alias = aliasList ?: aliasString?.split(",")?.map { it.trim() } ?: emptyList(),
                category = category,
                group = group,
                icon = icon
            )
        }
    }
}
