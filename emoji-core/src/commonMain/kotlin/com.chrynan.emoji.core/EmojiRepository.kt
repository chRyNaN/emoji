@file:Suppress("unused")

package com.chrynan.emoji.core

import com.chrynan.paginate.core.*

/**
 * Represents a class that can retrieve [Emoji]s from some source (database, web endpoint, etc).
 */
interface EmojiRepository {

    /**
     * Retrieves the [Emoji] with the provided [name] or throws [InvalidEmojiIdentifierException]
     * if there is no [Emoji] with the provided [name].
     */
    suspend fun getByName(name: String): Emoji

    /**
     * Retrieves the [Emoji] with the provided [alias] or throws [InvalidEmojiIdentifierException]
     * if there is no [Emoji] with the provided [alias].
     */
    suspend fun getByAlias(alias: String): Emoji

    /**
     * Retrieves the [EmojiCategory] with the provided [name] or throws
     * [InvalidEmojiCategoryIdentifierException] if there is no [EmojiCategory] with the provided
     * [name].
     *
     * Note that the default implementation performs a [getAll], then [categorize]s the resulting
     * [Emoji]s, and returns the first [EmojiCategory] with the provided [name] or throws
     * [InvalidEmojiCategoryIdentifierException] if there is no [EmojiCategory] with the provided
     * [name]. Implementations of [EmojiRepository] can provide more efficient implementations of
     * this function.
     */
    suspend fun getCategoryByName(name: String?): EmojiCategory =
        getAll().toList().categorize().firstOrNull { it.name == name }
            ?: throw InvalidEmojiIdentifierException(name)

    /**
     * Retrieves a [Sequence] of all [Emoji]s.
     */
    suspend fun getAll(): Sequence<Emoji>

    /**
     * Retrieves a [PaginateRepository] to paginate through all of the available [Emoji]s.
     *
     * Note that this defaults to returning a [BasePaginateSource] implementation whose
     * [BasePaginateSource.fetch] function simply returns a [PagedResult] containing the result of
     * a [getAll] function call. Implementations of the [EmojiRepository] may override this
     * functionality to be more performant and actually support pagination.
     *
     * @see [getAll]
     * @see [PaginateRepository]
     */
    suspend fun paginateAll(): PaginateRepository<Emoji, Emoji.Key> =
        object : BasePaginateSource<Emoji, Emoji.Key>() {

            override suspend fun fetch(
                count: Int,
                key: Emoji.Key?,
                direction: PageDirection,
                currentPageCount: Int
            ): PagedResult<Emoji, Emoji.Key> {
                val emojis = getAll().toList()

                val pageInfo = PageInfo(
                    index = 0,
                    hasPreviousPage = false,
                    hasNextPage = false,
                    firstKey = emojis.firstOrNull()?.key,
                    lastKey = emojis.lastOrNull()?.key
                )

                return PagedResult(
                    info = pageInfo,
                    items = emojis
                )
            }
        }

    /**
     * Retrieves a [PaginateRepository] to paginate through all of the available [Emoji]s for the
     * [EmojiCategory] with the provided [name].
     *
     * Note that the default implementation of this function simply returns a [BasePaginateSource]
     * whose [BasePaginateSource.fetch] function returns a [PagedResult] from calling
     * [getCategoryByName] and returning the [allEmojisFromCategories] on the returned
     * [EmojiCategory]. Implementations of this [EmojiRepository] can provide more efficient
     * implementations of this function.
     */
    suspend fun paginateAllFromCategoryByName(name: String?): PaginateRepository<Emoji, Emoji.Key> =
        object : BasePaginateSource<Emoji, Emoji.Key>() {

            override suspend fun fetch(
                count: Int,
                key: Emoji.Key?,
                direction: PageDirection,
                currentPageCount: Int
            ): PagedResult<Emoji, Emoji.Key> {
                val category = getCategoryByName(name = name)

                val items = category.allEmojis()

                val info = PageInfo(
                    index = 0,
                    hasNextPage = false,
                    hasPreviousPage = false,
                    firstKey = items.firstOrNull()?.key,
                    lastKey = items.lastOrNull()?.key
                )

                return PagedResult(
                    info = info,
                    items = items
                )
            }
        }

    /**
     * Searches for [Emoji]s by the provided [query] value. At the very least, this function should
     * search for an [Emoji] with an [Emoji.name] value equal to [query] and any [Emoji.aliases]
     * equal to [query].
     *
     * Note that this returns a [List] of [Emoji]s because it may be possible that the query
     * matches multiple [Emoji]s, such as, matching the name of one [Emoji] and the alias of
     * another.
     */
    suspend fun search(query: String): List<Emoji>

    /**
     * Retrieves all of the known [EmojiCategory] names represented by this [EmojiRepository].
     *
     * Note that the default implementation performs a [getAll], then calls [categorize] on the
     * resulting items, and [Collection.map]s them to a [Set] of unique [EmojiCategory] names.
     * Implementations of [EmojiRepository] can provide more efficient implementations of this
     * function.
     */
    suspend fun getAllCategoryNames(): Set<String?> =
        getAll().toList().categorize().map { it.name }.toSet()
}
