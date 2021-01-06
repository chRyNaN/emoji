package com.chrynan.emoji.core

/**
 * Represents a class that can mutate [Emoji]s from some source (database, web endpoint, etc).
 */
interface EmojiMutableRepository : EmojiRepository {

    suspend fun insert(emoji: Emoji)

    suspend fun update(emoji: Emoji)

    suspend fun delete(emoji: Emoji)

    suspend fun deleteAll()
}
