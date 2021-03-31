@file:Suppress("unused")

package com.chrynan.emoji.core

/**
 * Retrieves the [Emoji] with the provided [name] or null if there is no [Emoji] with the provided
 * [name].
 *
 * @see [EmojiRepository.getByName]
 */
suspend fun EmojiRepository.getByNameOrNull(name: String): Emoji? =
    try {
        getByName(name = name)
    } catch (throwable: InvalidEmojiIdentifierException) {
        null
    }

/**
 * Retrieves the [Emoji] with the provided [alias] or null if there is no [Emoji] with the provided
 * [alias].
 *
 * @see [EmojiRepository.getByAlias]
 */
suspend fun EmojiRepository.getByAliasOrNull(alias: String): Emoji? =
    try {
        getByAlias(alias = alias)
    } catch (throwable: InvalidEmojiIdentifierException) {
        null
    }

/**
 * Retrieves the [EmojiCategory] with the provided [name] or null if there is no [EmojiCategory]
 * whose [EmojiCategory.name] value matches the provided [name].
 *
 * @see [EmojiRepository.getCategoryByName]
 */
suspend fun EmojiRepository.getCategoryByNameOrNull(name: String?): EmojiCategory? =
    try {
        getCategoryByName(name = name)
    } catch (throwable: InvalidEmojiCategoryIdentifierException) {
        null
    }
