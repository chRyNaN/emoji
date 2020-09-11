@file:Suppress("MemberVisibilityCanBePrivate")

package com.chrynan.emoji.repo.sqlite

import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.core.InvalidEmojiIdentifierException

/**
 * An implementation of the [EmojiRepository] that uses an SqlDelight database, [EmojiDatabase], for the data set.
 *
 * Note that before using this repository for the first time, the [SqliteEmojiRepository.init] function should be
 * called. The [SqliteEmojiRepository.init] function sets up the initial data set by loading all of the [Emoji]s into
 * the database.
 */
class SqliteEmojiRepository(internal val database: EmojiDatabase) : EmojiRepository {

    private val mapper = SqliteEmojiMapper()

    override suspend fun getByName(name: String): Emoji {
        val sqliteEmoji = database.emojiQueries.getByName(name = name).executeAsList().firstOrNull()
            ?: throw InvalidEmojiIdentifierException(identifier = name)

        return mapper.map(sqliteEmoji)
    }

    override suspend fun getByAlias(alias: String): Emoji {
        val sqliteEmoji = database.emojiQueries.getByAlias(alias = alias).executeAsList().firstOrNull()
            ?: throw InvalidEmojiIdentifierException(identifier = alias)

        return mapper.map(sqliteEmoji)
    }

    override suspend fun getAll(): Sequence<Emoji> {
        val sqliteEmojis = database.emojiQueries.getAll().executeAsList()

        return sqliteEmojis.asSequence().map { mapper.map(it) }
    }
}
