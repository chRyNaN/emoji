@file:Suppress("unused")

package com.chrynan.emoji.core

/**
 * Represents a class that can retrieve [Emoji]s from some source (database, web endpoint, etc).
 */
interface EmojiRepository {

    /**
     * Retrieves the [Emoji] with the provided [name] or throws [InvalidEmojiIdentifierException] if there is no
     * [Emoji] with the provided [name].
     */
    suspend fun getByName(name: String): Emoji

    /**
     * Retrieves the [Emoji] with the provided [alias] or throws [InvalidEmojiIdentifierException] if there is no
     * [Emoji] with the provided [alias].
     */
    suspend fun getByAlias(alias: String): Emoji

    /**
     * Retrieves a [Sequence] of all [Emoji]s.
     */
    suspend fun getAll(): Sequence<Emoji>
}
