@file:Suppress("unused", "UnusedImport")

package com.chrynan.emoji.repo.sqlite

import com.chrynan.emoji.repo.sqlite.SqliteEmojiRepository

/**
 * Initializes this [SqliteEmojiRepository] with the initial data set.
 *
 * Note that this should be called before calling any of the other [SqliteEmojiRepository] functions, otherwise the
 * internal database will be empty and no values can be retrieved.
 */
@Suppress("RedundantSuspendModifier")
suspend fun SqliteEmojiRepository.init() {
        database.emojiQueries.initialInsert0()
        database.emojiQueries.initialInsert1()
        database.emojiQueries.initialInsert2()
        database.emojiQueries.initialInsert3()
        database.emojiQueries.initialInsert4()
        database.emojiQueries.initialInsert5()
}
