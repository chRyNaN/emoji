package com.chrynan.emoji.repo.sqlite

/**
 * Converts a generated SqlDelight [Emoji] instance to a [com.chrynan.emoji.core.Emoji] instance. This is used in the
 * [SqliteEmojiRepository].
 *
 * Note that this is an internal class and isn't necessary to be public facing.
 */
internal class SqliteEmojiMapper {

    fun map(sqliteEmoji: Emoji): com.chrynan.emoji.core.Emoji =
        com.chrynan.emoji.core.Emoji(
            unicodeString = sqliteEmoji.unicode,
            char = sqliteEmoji.char,
            name = sqliteEmoji.name,
            alias = sqliteEmoji.alias,
            category = sqliteEmoji.category,
            group = sqliteEmoji.group,
            iconUri = sqliteEmoji.icon
        )
}
