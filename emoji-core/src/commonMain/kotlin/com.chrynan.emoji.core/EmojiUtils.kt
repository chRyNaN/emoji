@file:Suppress("unused")

package com.chrynan.emoji.core

/**
 * Converts this [Collection] of [Emoji]s into a [List] of [EmojiCategory] by grouping and
 * categorizing each [Emoji].
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
 * Converts this [Collection] of [Emoji]s into a [List] of [EmojiGroup]s that are derived by the
 * [Emoji.name] property instead of the [Emoji.group] property. This allows [Emoji]s with the same
 * [Emoji.name] to be grouped together which may possibly be the same [Emoji] but with different
 * [Emoji.variant]s.
 */
fun Collection<Emoji>.groupByName(): List<EmojiGroup> =
    groupBy { it.name }.map { groupMap ->
        EmojiGroup(
            name = groupMap.key,
            emojis = groupMap.value,
            icon = groupMap.value.firstOrNull()
        )
    }

/**
 * Converts this [Map] with a value of [Emoji] into a [List] of [EmojiCategory].
 */
fun <K> Map<K, Emoji>.categorize(): List<EmojiCategory> = entries.map { it.value }.categorize()

/**
 * Converts this [Map] with a value of [Emoji] into a [List] of [EmojiGroup].
 */
fun <K> Map<K, Emoji>.group(): List<EmojiGroup> = entries.map { it.value }.group()

/**
 * Converts this [Map] of [Emoji] values into a [List] of [EmojiGroup]s that are derived by the
 * [Emoji.name] property instead of the [Emoji.group] property. This allows [Emoji]s with the same
 * [Emoji.name] to be grouped together which may possibly be the same [Emoji] but with different
 * [Emoji.variant]s.
 */
fun <K> Map<K, Emoji>.groupByName(): List<EmojiGroup> = entries.map { it.value }.groupByName()

/**
 * Converts this [Collection] of [EmojiCategory] into a [List] of all of the [Emoji]s from every
 * [EmojiGroup] in every [EmojiCategory] in this [Collection].
 */
fun Collection<EmojiCategory>.allEmojisFromCategories(): List<Emoji> =
    flatMap { it.groups.allEmojisFromGroups() }

/**
 * Converts this [Collection] of [EmojiCategory] into a [List] of all of the [Emoji]s from every
 * [Emoji] in this [Collection].
 */
fun Collection<EmojiGroup>.allEmojisFromGroups(): List<Emoji> = flatMap { it.emojis }

/**
 * Retrieves a [List] of all of the [Emoji]s belonging to all of the [EmojiGroup]s in this
 * [EmojiCategory].
 */
fun EmojiCategory.allEmojis(): List<Emoji> =
    groups.allEmojisFromGroups()

/**
 * Converts this [Emoji.name] to a name that is useful for quick lookup. The resulting [String] is
 * equivalent to "$lookupChar$name$lookupChar".
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
 * Converts these [Emoji.aliases] to aliases that are useful for quick lookup. The resulting
 * [String]s are equivalent to "$lookupChar$alias$lookupChar".
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
 * Converts this [CharSequence] shortcode to a value that is useful for quick lookup. The resulting
 * [String] is equivalent to "$lookupChar$this$lookupChar".
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

/**
 * A name for this [Emoji] that is useful for accessibility services.
 */
val Emoji.defaultAccessibilityName: String
    get() = buildString {
        append(this@defaultAccessibilityName.name)

        val variant = this@defaultAccessibilityName.variant

        if (variant != null) {
            append(" $variant")
        }
    }

@ExperimentalEmojiApi
operator fun Emoji.Unicode.plus(other: Emoji.Unicode): Emoji.Unicode =
    Emoji.Unicode(
        name = "${this.name} ${other.name}",
        aliases = this.aliases + other.aliases,
        category = this.category,
        group = this.group,
        variant = when {
            this.variant != null && other.variant != null -> "${this.variant}+${other.variant}"
            this.variant != null -> this.variant
            other.variant != null -> other.variant
            else -> null
        },
        iconUri = null,
        unicodeString = "${this.unicodeString}${other.unicodeString}",
        unicodeList = this.unicodeList + other.unicodeList,
        character = "${this.character}${other.character}"
    )
