//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiMutableRepository](index.md)

# EmojiMutableRepository

[common]\
interface [EmojiMutableRepository](index.md) : [EmojiRepository](../-emoji-repository/index.md)

Represents a class that can mutate an [Emoji](../-emoji/index.md)s source (database, web endpoint, etc).

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [common]<br>abstract suspend fun [delete](delete.md)(emoji: [Emoji](../-emoji/index.md))<br>Deletes the provided [emoji](delete.md) from the repository. |
| [deleteAll](delete-all.md) | [common]<br>abstract suspend fun [deleteAll](delete-all.md)()<br>Deletes all of the [Emoji](../-emoji/index.md)s in this repository. |
| [getAll](../-emoji-repository/get-all.md) | [common]<br>abstract suspend fun [getAll](../-emoji-repository/get-all.md)(): [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>Retrieves a [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html) of all [Emoji](../-emoji/index.md)s. |
| [getAllCategoryNames](../-emoji-repository/get-all-category-names.md) | [common]<br>open suspend fun [getAllCategoryNames](../-emoji-repository/get-all-category-names.md)(): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?&gt;<br>Retrieves all of the known [EmojiCategory](../-emoji-category/index.md) names represented by this [EmojiRepository](../-emoji-repository/index.md). |
| [getByAlias](../-emoji-repository/get-by-alias.md) | [common]<br>abstract suspend fun [getByAlias](../-emoji-repository/get-by-alias.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji](../-emoji/index.md)<br>Retrieves the [Emoji](../-emoji/index.md) with the provided [alias](../-emoji-repository/get-by-alias.md) or throws [InvalidEmojiIdentifierException](../-invalid-emoji-identifier-exception/index.md) if there is no [Emoji](../-emoji/index.md) with the provided [alias](../-emoji-repository/get-by-alias.md). |
| [getByName](../-emoji-repository/get-by-name.md) | [common]<br>abstract suspend fun [getByName](../-emoji-repository/get-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji](../-emoji/index.md)<br>Retrieves the [Emoji](../-emoji/index.md) with the provided [name](../-emoji-repository/get-by-name.md) or throws [InvalidEmojiIdentifierException](../-invalid-emoji-identifier-exception/index.md) if there is no [Emoji](../-emoji/index.md) with the provided [name](../-emoji-repository/get-by-name.md). |
| [getCategoryByName](../-emoji-repository/get-category-by-name.md) | [common]<br>open suspend fun [getCategoryByName](../-emoji-repository/get-category-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [EmojiCategory](../-emoji-category/index.md)<br>Retrieves the [EmojiCategory](../-emoji-category/index.md) with the provided [name](../-emoji-repository/get-category-by-name.md) or throws [InvalidEmojiCategoryIdentifierException](../-invalid-emoji-category-identifier-exception/index.md) if there is no [EmojiCategory](../-emoji-category/index.md) with the provided [name](../-emoji-repository/get-category-by-name.md). |
| [insert](insert.md) | [common]<br>abstract suspend fun [insert](insert.md)(emoji: [Emoji](../-emoji/index.md))<br>Inserts the provided [emoji](insert.md) into the repository. |
| [paginateAll](../-emoji-repository/paginate-all.md) | [common]<br>open suspend fun [paginateAll](../-emoji-repository/paginate-all.md)(): PaginateRepository&lt;[Emoji.Key](../-emoji/-key/index.md), [Emoji](../-emoji/index.md)&gt;<br>Retrieves a PaginateRepository to paginate through all of the available [Emoji](../-emoji/index.md)s. |
| [paginateAllFromCategoryByName](../-emoji-repository/paginate-all-from-category-by-name.md) | [common]<br>open suspend fun [paginateAllFromCategoryByName](../-emoji-repository/paginate-all-from-category-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): PaginateRepository&lt;[Emoji.Key](../-emoji/-key/index.md), [Emoji](../-emoji/index.md)&gt;<br>Retrieves a PaginateRepository to paginate through all of the available [Emoji](../-emoji/index.md)s for the [EmojiCategory](../-emoji-category/index.md) with the provided [name](../-emoji-repository/paginate-all-from-category-by-name.md). |
| [search](../-emoji-repository/search.md) | [common]<br>abstract suspend fun [search](../-emoji-repository/search.md)(query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>Searches for [Emoji](../-emoji/index.md)s by the provided [query](../-emoji-repository/search.md) value. At the very least, this function should search for an [Emoji](../-emoji/index.md) with an [Emoji.name](../-emoji/name.md) value equal to [query](../-emoji-repository/search.md) and any [Emoji.aliases](../-emoji/aliases.md) equal to [query](../-emoji-repository/search.md). |
| [update](update.md) | [common]<br>abstract suspend fun [update](update.md)(emoji: [Emoji](../-emoji/index.md))<br>Updates the provided [emoji](update.md) in the repository. |