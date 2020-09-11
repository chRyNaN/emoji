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
