@file:Suppress("MemberVisibilityCanBePrivate")

package com.chrynan.emoji.repo.map

import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.core.EmojiMutableRepository
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.core.InvalidEmojiIdentifierException

/**
 * An implementation of [EmojiRepository] that uses a Kotlin [Map] for the data set.
 *
 * Note that before using this repository for the first time, the KotlinMapEmojiRepository.init() function should be
 * called. The KotlinMapEmojiRepository.init() function sets up the initial data set by loading all of the [Emoji]s
 * into the map.
 */
class KotlinMapEmojiRepository : EmojiRepository,
    EmojiMutableRepository {

    internal val map = mutableMapOf<String, Emoji>()

    override suspend fun getByName(name: String): Emoji =
        map[name] ?: throw InvalidEmojiIdentifierException(identifier = name)

    override suspend fun getByAlias(alias: String): Emoji =
        map.entries.firstOrNull { it.value.aliases.contains(alias) }?.value
            ?: throw InvalidEmojiIdentifierException(identifier = alias)

    override suspend fun getAll(): Sequence<Emoji> = map.asSequence().map { it.value }

    override suspend fun insert(emoji: Emoji) {
        if (map[emoji.name] != null) throw IllegalStateException("Cannot insert Emoji since it already exists.")

        map[emoji.name] = emoji
    }

    override suspend fun update(emoji: Emoji) {
        map[emoji.name] = emoji
    }

    override suspend fun delete(emoji: Emoji) {
        map.remove(emoji.name)
    }

    override suspend fun deleteAll() {
        map.clear()
    }
}
