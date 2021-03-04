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
fun Collection<EmojiCategory>.allEmojisFromCategories(): List<Emoji> =
    flatMap { it.groups.allEmojisFromGroups() }

/**
 * Converts this [Collection] of [EmojiCategory] into a [List] of all of the [Emoji]s from every [Emoji] in this
 * [Collection].
 */
fun Collection<EmojiGroup>.allEmojisFromGroups(): List<Emoji> = flatMap { it.emojis }

/**
 * Converts this [Emoji.name] to a name that is useful for quick lookup. The resulting [String] is equivalent to
 * "$lookupChar$name$lookupChar".
 *
 * @param [lookupChar] The prefix and suffix character used in the resulting [String].
 *
 * @param [allowDuplicateLookupChars] Whether to allow the [lookupChar] to have consecutive
 * duplicates in the prefix and suffix. If this value is true, then no check will be performed to
 * see if the name already begins or ends with the [lookupChar] and it will just be appended to
 * the start and end of the name. If this value is false, then the name will be checked if it
 * already starts or ends with the [lookupChar], if it does, the additional [lookupChar] will not
 * be appended.
 */
fun Emoji.shortCodeName(
    lookupChar: Char = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
): String = buildString {
    if (allowDuplicateLookupChars) {
        append("$lookupChar$name$lookupChar")
    } else {
        if (!name.startsWith(lookupChar)) {
            append(lookupChar)
        }

        append(name)

        if (!name.endsWith(lookupChar)) {
            append(lookupChar)
        }
    }
}

/**
 * Converts these [Emoji.aliases] to aliases that are useful for quick lookup. The resulting [String]s are equivalent to
 * "$lookupChar$alias$lookupChar".
 *
 * @param [lookupChar] The prefix and suffix character used in the resulting [String]s.
 *
 * @param [allowDuplicateLookupChars] Whether to allow the [lookupChar] to have consecutive
 * duplicates in the prefix and suffix. If this value is true, then no check will be performed to
 * see if the alias already begins or ends with the [lookupChar] and it will just be appended to
 * the start and end of the alias. If this value is false, then the alias will be checked if it
 * already starts or ends with the [lookupChar], if it does, the additional [lookupChar] will not
 * be appended.
 */
fun Emoji.shortCodeAliases(
    lookupChar: Char = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
): List<String> =
    aliases.map {
        buildString {
            if (allowDuplicateLookupChars) {
                append("$lookupChar$name$lookupChar")
            } else {
                if (!name.startsWith(lookupChar)) {
                    append(lookupChar)
                }

                append(name)

                if (!name.endsWith(lookupChar)) {
                    append(lookupChar)
                }
            }
        }
    }

/**
 * Determines whether this [Emoji.name] or any [Emoji.aliases] contains the provided [name].
 */
operator fun Emoji.contains(name: String): Boolean =
    this.name.contains(name) || this.aliases.any { it.contains(name) }

/**
 * Determines whether this [Emoji.name] or any [Emoji.aliases] contains the provided [name] using
 * the provided [ignoreCase] value.
 */
fun Emoji.contains(name: String, ignoreCase: Boolean): Boolean =
    this.name.contains(name, ignoreCase) || this.aliases.any { it.contains(name, ignoreCase) }

/**
 * Determines whether this [Emoji.shortCodeName] or any [Emoji.shortCodeAliases] contains the
 * provided [shortCode] using the provided [ignoreCase] and [allowDuplicateLookupChars] values.
 */
fun Emoji.containsShortCode(
    shortCode: String,
    ignoreCase: Boolean = false,
    lookupChar: Char = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
): Boolean {
    val name = this.shortCodeName(
        lookupChar = lookupChar,
        allowDuplicateLookupChars = allowDuplicateLookupChars
    )
    val aliases = this.shortCodeAliases(
        lookupChar = lookupChar,
        allowDuplicateLookupChars = allowDuplicateLookupChars
    )

    return name.contains(shortCode, ignoreCase) || aliases.any {
        it.contains(
            shortCode,
            ignoreCase
        )
    }
}

/**
 * Converts this [CharSequence] shortcode to a value that is useful for quick lookup. The resulting [String] is
 * equivalent to "$lookupChar$this$lookupChar".
 *
 * @param [lookupChar] The prefix and suffix character used in the resulting [String]s.
 *
 * @param [allowDuplicateLookupChars] Whether to allow the [lookupChar] to have consecutive
 * duplicates in the prefix and suffix. If this value is true, then no check will be performed to
 * see if the alias already begins or ends with the [lookupChar] and it will just be appended to
 * the start and end of the alias. If this value is false, then the alias will be checked if it
 * already starts or ends with the [lookupChar], if it does, the additional [lookupChar] will not
 * be appended.
 */
fun CharSequence.asEmojiShortCode(
    lookupChar: Char = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
): String = buildString {
    if (allowDuplicateLookupChars) {
        append("$lookupChar$this$lookupChar")
    } else {
        if (!this.startsWith(lookupChar)) {
            append(lookupChar)
        }

        append(this)

        if (!this.endsWith(lookupChar)) {
            append(lookupChar)
        }
    }
}
