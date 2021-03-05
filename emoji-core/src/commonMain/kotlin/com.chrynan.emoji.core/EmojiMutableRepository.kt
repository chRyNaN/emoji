package com.chrynan.emoji.core

/**
 * Represents a class that can mutate an [Emoji]s source (database, web endpoint, etc).
 */
interface EmojiMutableRepository : EmojiRepository {

    /**
     * Inserts the provided [emoji] into the repository.
     */
    suspend fun insert(emoji: Emoji)

    /**
     * Updates the provided [emoji] in the repository.
     */
    suspend fun update(emoji: Emoji)

    /**
     * Deletes the provided [emoji] from the repository.
     */
    suspend fun delete(emoji: Emoji)

    /**
     * Deletes all of the [Emoji]s in this repository.
     */
    suspend fun deleteAll()
}
