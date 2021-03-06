@file:Suppress("unused")

package com.chrynan.emoji.core

/**
 * Converts this [Collection] of [Emoji]s into a [List] of [EmojiCategory] by grouping and categorizing each [Emoji].
 */
fun Collection<Emoji>.categorize(): List<EmojiCategory> =
    groupBy { it.category }.map { categoryMap ->
        EmojiCategory(
            name = categoryMap.key,
            groups = categoryMap.value.groupBy { it.group }.map { groupMap ->
                EmojiGroup(
                    name = groupMap.key,
                    emojis = groupMap.value,
                    icon = groupMap.value.firstOrNull()
                )
            },
            icon = categoryMap.value.firstOrNull()
        )
    }

/**
 * Converts this [Collection] of [Emoji]s into a [List] of [EmojiGroup]s.
 */
fun Collection<Emoji>.group(): List<EmojiGroup> =
    groupBy { it.group }.map { groupMap ->
        EmojiGroup(
            name = groupMap.key,
            emojis = groupMap.value,
            icon = groupMap.value.firstOrNull()
        )
    }

/**
 * Converts this [Map] with a value of [Emoji] into a [List] of [EmojiCategory].
 */
fun <T> Map<T, Emoji>.categorize(): List<EmojiCategory> = entries.map { it.value }.categorize()

/**
 * Converts this [Map] with a value of [Emoji] into a [List] of [EmojiGroup].
 */
fun <T> Map<T, Emoji>.group(): List<EmojiGroup> = entries.map { it.value }.group()

/**
 * Converts this [Collection] of [EmojiCategory] into a [List] of all of the [Emoji]s from every [EmojiGroup] in every
 * [EmojiCategory] in this [Collection].
 */
fun Collection<EmojiCategory>.allEmojisFromCategories(): List<Emoji> = flatMap { it.groups.allEmojisFromGroups() }

/**
 * Converts this [Collection] of [EmojiCategory] into a [List] of all of the [Emoji]s from every [Emoji] in this
 * [Collection].
 */
fun Collection<EmojiGroup>.allEmojisFromGroups(): List<Emoji> = flatMap { it.emojis }

/**
 * Converts this [Emoji.name] to a name that is useful for quick lookup. The resulting [String] is equivalent to
 * "$lookupChar$name$lookupChar".
 *
 * Note that this does not check if the name already begins or ends with the [lookupChar].
 */
fun Emoji.shortcodeName(lookupChar: Char = Emoji.DEFAULT_SHORTCODE_CHAR): String = "$lookupChar$name$lookupChar"

/**
 * Converts these [Emoji.aliases] to aliases that are useful for quick lookup. The resulting [String]s are equivalent to
 * "$lookupChar$alias$lookupChar".
 *
 * Note that this does not check if the aliases already begin or end with the [lookupChar].
 */
fun Emoji.shortcodeAliases(lookupChar: Char = Emoji.DEFAULT_SHORTCODE_CHAR): List<String> =
    aliases.map { "$lookupChar$it$lookupChar" }

operator fun Emoji.contains(name: String): Boolean = this.name.contains(name) || this.aliases.any { it.contains(name) }

fun Emoji.contains(name: String, ignoreCase: Boolean): Boolean =
    this.name.contains(name, ignoreCase) || this.aliases.any { it.contains(name, ignoreCase) }

fun Emoji.containsShortcode(
    shortcode: String,
    lookupChar: Char = Emoji.DEFAULT_SHORTCODE_CHAR,
    ignoreCase: Boolean = false
): Boolean {
    val formattedShortcode = shortcode.removePrefix("$lookupChar").removeSuffix("$lookupChar")

    return contains(formattedShortcode, ignoreCase)
}

/**
 * Converts this [CharSequence] shortcode to a value that is useful for quick lookup. The resulting [String] is
 * equivalent to "$lookupChar$this$lookupChar".
 *
 * Note that this does not check if this [CharSequence] already begins or ends with the [lookupChar].
 */
fun CharSequence.asShortcode(lookupChar: Char = Emoji.DEFAULT_SHORTCODE_CHAR): String = "$lookupChar$this$lookupChar"
