package com.chrynan.emoji.repo.sqlite

/**
 * Converts a generated SqlDelight [Emoji] instance to a [com.chrynan.emoji.core.Emoji] instance. This is used in the
 * [SqliteEmojiRepository].
 *
 * Note that this is an internal class and isn't necessary to be public facing.
 */
internal class SqliteEmojiMapper {

    fun map(sqliteEmoji: Emoji): com.chrynan.emoji.core.Emoji =
        if (sqliteEmoji.type == com.chrynan.emoji.core.Emoji.Unicode.TYPE_NAME && sqliteEmoji.unicode != null && sqliteEmoji.char != null) {
            com.chrynan.emoji.core.Emoji.Unicode(
                unicodeString = sqliteEmoji.unicode,
                char = sqliteEmoji.char,
                name = sqliteEmoji.name,
                aliases = sqliteEmoji.alias.split(","),
                category = sqliteEmoji.category,
                group = sqliteEmoji.group,
                iconUri = sqliteEmoji.icon
            )
        } else {
            com.chrynan.emoji.core.Emoji.Custom(
                name = sqliteEmoji.name,
                aliases = sqliteEmoji.alias.split(","),
                category = sqliteEmoji.category,
                group = sqliteEmoji.group,
                uri = (sqliteEmoji.uri ?: sqliteEmoji.icon)!!,
                staticUri = sqliteEmoji.static_uri,
                mimeType = sqliteEmoji.mime_type
            )
        }
}
