@file:Suppress("MemberVisibilityCanBePrivate")

package com.chrynan.emoji.repo.map

import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.core.InvalidEmojiIdentifierException

/**
 * An implementation of [EmojiRepository] that uses a Kotlin [Map] for the data set.
 *
 * Note that before using this repository for the first time, the KotlinMapEmojiRepository.init() function should be
 * called. The KotlinMapEmojiRepository.init() function sets up the initial data set by loading all of the [Emoji]s
 * into the map.
 */
class KotlinMapEmojiRepository : EmojiRepository {

    internal val map = mutableMapOf<String, Emoji>()

    override suspend fun getByName(name: String): Emoji =
        map[name] ?: throw InvalidEmojiIdentifierException(identifier = name)

    override suspend fun getByAlias(alias: String): Emoji = map.entries.firstOrNull { it.value.alias == alias }?.value
        ?: throw InvalidEmojiIdentifierException(identifier = alias)

    override suspend fun getAll(): Sequence<Emoji> = map.asSequence().map { it.value }
}
