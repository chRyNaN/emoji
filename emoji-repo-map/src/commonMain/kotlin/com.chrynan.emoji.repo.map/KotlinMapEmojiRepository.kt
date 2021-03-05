@file:Suppress("MemberVisibilityCanBePrivate")

package com.chrynan.emoji.repo.map

import com.chrynan.emoji.core.*
import com.chrynan.paginate.core.*

/**
 * An implementation of [EmojiRepository] that uses a Kotlin [Map] for the data set.
 *
 * Note that before using this repository for the first time, the KotlinMapEmojiRepository.init() function should be
 * called. The KotlinMapEmojiRepository.init() function sets up the initial data set by loading all of the [Emoji]s
 * into the map.
 */
class KotlinMapEmojiRepository : EmojiRepository,
    EmojiMutableRepository {

    internal val map = mutableMapOf<Emoji.Key, Emoji>()

    override suspend fun getByName(name: String): Emoji =
        map.entries.firstOrNull { it.value.name == name }?.value
            ?: throw InvalidEmojiIdentifierException(identifier = name)

    override suspend fun getByAlias(alias: String): Emoji =
        map.entries.firstOrNull { it.value.aliases.contains(alias) }?.value
            ?: throw InvalidEmojiIdentifierException(identifier = alias)

    override suspend fun getAll(): Sequence<Emoji> = map.asSequence().map { it.value }

    override suspend fun paginateAll(): PaginateRepository<Emoji, Emoji.Key> =
        object : BasePaginateSource<Emoji, Emoji.Key>() {

            override suspend fun fetch(
                count: Int,
                key: Emoji.Key?,
                direction: PageDirection,
                currentPageCount: Int
            ): PagedResult<Emoji, Emoji.Key> {
                val entries = map.entries
                val startIndex = if (key == null) 0 else entries.indexOfFirst { it.key == key }

                if (startIndex == -1) {
                    return PagedResult(
                        info = PageInfo(
                            index = currentPageCount,
                            hasPreviousPage = if (direction == PageDirection.AFTER) key != null && currentPageCount >= 1 else false,
                            hasNextPage = direction == PageDirection.BEFORE,
                            firstKey = null,
                            lastKey = null
                        ),
                        items = emptyList()
                    )
                }

                val items = mutableListOf<Emoji>()

                if (direction == PageDirection.AFTER) {
                    for (i in startIndex until startIndex + count) {
                        items.add(entries.elementAt(i).value)
                    }
                } else {
                    for (i in startIndex downTo startIndex - count) {
                        items.add(entries.elementAt(i).value)
                    }
                }

                val info = PageInfo(
                    index = currentPageCount,
                    hasPreviousPage = startIndex > 0,
                    hasNextPage = startIndex < entries.size,
                    firstKey = items.firstOrNull()?.key,
                    lastKey = items.lastOrNull()?.key
                )

                return PagedResult(
                    info = info,
                    items = items
                )
            }
        }

    override suspend fun search(query: String): List<Emoji> {
        val nameEmoji = getByNameOrNull(query)
        val aliasEmoji = getByAliasOrNull(query)

        return listOfNotNull(nameEmoji, aliasEmoji)
    }

    override suspend fun insert(emoji: Emoji) {
        if (map[emoji.key] != null) throw IllegalStateException("Cannot insert Emoji since it already exists.")

        map[emoji.key] = emoji
    }

    override suspend fun update(emoji: Emoji) {
        map[emoji.key] = emoji
    }

    override suspend fun delete(emoji: Emoji) {
        map.remove(emoji.key)
    }

    override suspend fun deleteAll() {
        map.clear()
    }
}
