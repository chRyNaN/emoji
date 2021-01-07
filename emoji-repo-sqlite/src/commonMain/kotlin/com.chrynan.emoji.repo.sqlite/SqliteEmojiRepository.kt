@file:Suppress("MemberVisibilityCanBePrivate")

package com.chrynan.emoji.repo.sqlite

import com.chrynan.emoji.core.*
import com.chrynan.emoji.core.Emoji

/**
 * An implementation of the [EmojiRepository] that uses an SqlDelight database, [EmojiDatabase], for the data set.
 *
 * Note that before using this repository for the first time, the [SqliteEmojiRepository.init] function should be
 * called. The [SqliteEmojiRepository.init] function sets up the initial data set by loading all of the [Emoji]s into
 * the database.
 */
class SqliteEmojiRepository(internal val database: EmojiDatabase) : EmojiRepository,
    EmojiMutableRepository {

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

    override suspend fun search(query: String): List<Emoji> =
        database.emojiQueries.search(query).executeAsList().map {
            mapper.map(it)
        }

    override suspend fun insert(emoji: Emoji) {
        database.emojiQueries.transaction {
            if (database.emojiQueries.getByName(name = emoji.name).executeAsList()
                    .firstOrNull() != null
            ) throw IllegalStateException("Cannot insert Emoji since it already exists.")

            database.emojiQueries.insert(mapper.mapToDb(emoji))
        }
    }

    override suspend fun update(emoji: Emoji) {
        database.emojiQueries.update(
            type = emoji.typeName,
            name = emoji.name,
            alias = emoji.aliases.joinToString(","),
            category = emoji.category,
            group = emoji.group,
            unicode = if (emoji is Emoji.Unicode) emoji.unicodeString else null,
            char = if (emoji is Emoji.Unicode) emoji.char else null,
            icon = if (emoji is Emoji.Unicode) emoji.iconUri else null,
            uri = if (emoji is Emoji.Custom) emoji.uri else null,
            static_uri = if (emoji is Emoji.Custom) emoji.staticUri else null,
            mime_type = if (emoji is Emoji.Custom) emoji.mimeType else null
        )
    }

    override suspend fun delete(emoji: Emoji) = database.emojiQueries.delete(emoji.name)

    override suspend fun deleteAll() = database.emojiQueries.deleteAll()
}
