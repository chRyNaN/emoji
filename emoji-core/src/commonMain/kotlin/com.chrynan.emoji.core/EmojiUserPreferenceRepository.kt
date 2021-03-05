@file:Suppress("unused")

package com.chrynan.emoji.core

/**
 * A repository that retrieves [Emoji]s related to a User's preference and actions.
 */
interface EmojiUserPreferenceRepository {

    /**
     * Retrieves a [Sequence] of recently used [Emoji]s by the current User.
     */
    suspend fun getAllRecentlyUsed(): Sequence<Emoji>

    /**
     * Retrieves a [Sequence] of [Emoji]s that are most used by the current User.
     */
    suspend fun getAllMostUsed(): Sequence<Emoji>

    /**
     * Retrieves a [Sequence] of preferred, or favorited, [Emoji]s by the current User.
     */
    suspend fun getAllPreferred(): Sequence<Emoji>
}
