@file:Suppress("unused")

package com.chrynan.emoji.core

/**
 * Retrieves the [Emoji] with the provided [name] or null if there is no [Emoji] with the provided
 * [name].
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
 */
suspend fun EmojiRepository.getByAliasOrNull(alias: String): Emoji? =
    try {
        getByAlias(alias = alias)
    } catch (throwable: InvalidEmojiIdentifierException) {
        null
    }
