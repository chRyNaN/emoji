//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiRepository](index.md)

# EmojiRepository

[common]\
interface [EmojiRepository](index.md)

Represents a class that can retrieve [Emoji](../-emoji/index.md)s from some source (database, web endpoint, etc).

## Functions

| Name | Summary |
|---|---|
| [getAll](get-all.md) | [common]<br>abstract suspend fun [getAll](get-all.md)(): [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>Retrieves a [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html) of all [Emoji](../-emoji/index.md)s. |
| [getAllCategoryNames](get-all-category-names.md) | [common]<br>open suspend fun [getAllCategoryNames](get-all-category-names.md)(): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?&gt;<br>Retrieves all of the known [EmojiCategory](../-emoji-category/index.md) names represented by this [EmojiRepository](index.md). |
| [getByAlias](get-by-alias.md) | [common]<br>abstract suspend fun [getByAlias](get-by-alias.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji](../-emoji/index.md)<br>Retrieves the [Emoji](../-emoji/index.md) with the provided [alias](get-by-alias.md) or throws [InvalidEmojiIdentifierException](../-invalid-emoji-identifier-exception/index.md) if there is no [Emoji](../-emoji/index.md) with the provided [alias](get-by-alias.md). |
| [getByName](get-by-name.md) | [common]<br>abstract suspend fun [getByName](get-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji](../-emoji/index.md)<br>Retrieves the [Emoji](../-emoji/index.md) with the provided [name](get-by-name.md) or throws [InvalidEmojiIdentifierException](../-invalid-emoji-identifier-exception/index.md) if there is no [Emoji](../-emoji/index.md) with the provided [name](get-by-name.md). |
| [getCategoryByName](get-category-by-name.md) | [common]<br>open suspend fun [getCategoryByName](get-category-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [EmojiCategory](../-emoji-category/index.md)<br>Retrieves the [EmojiCategory](../-emoji-category/index.md) with the provided [name](get-category-by-name.md) or throws [InvalidEmojiCategoryIdentifierException](../-invalid-emoji-category-identifier-exception/index.md) if there is no [EmojiCategory](../-emoji-category/index.md) with the provided [name](get-category-by-name.md). |
| [paginateAll](paginate-all.md) | [common]<br>open suspend fun [paginateAll](paginate-all.md)(): PaginateRepository&lt;[Emoji.Key](../-emoji/-key/index.md), [Emoji](../-emoji/index.md)&gt;<br>Retrieves a PaginateRepository to paginate through all of the available [Emoji](../-emoji/index.md)s. |
| [paginateAllFromCategoryByName](paginate-all-from-category-by-name.md) | [common]<br>open suspend fun [paginateAllFromCategoryByName](paginate-all-from-category-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): PaginateRepository&lt;[Emoji.Key](../-emoji/-key/index.md), [Emoji](../-emoji/index.md)&gt;<br>Retrieves a PaginateRepository to paginate through all of the available [Emoji](../-emoji/index.md)s for the [EmojiCategory](../-emoji-category/index.md) with the provided [name](paginate-all-from-category-by-name.md). |
| [search](search.md) | [common]<br>abstract suspend fun [search](search.md)(query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>Searches for [Emoji](../-emoji/index.md)s by the provided [query](search.md) value. At the very least, this function should search for an [Emoji](../-emoji/index.md) with an [Emoji.name](../-emoji/name.md) value equal to [query](search.md) and any [Emoji.aliases](../-emoji/aliases.md) equal to [query](search.md). |

## Inheritors

| Name |
|---|
| [EmojiMutableRepository](../-emoji-mutable-repository/index.md) |

## Extensions

| Name | Summary |
|---|---|
| [getByAliasOrNull](../get-by-alias-or-null.md) | [common]<br>suspend fun [EmojiRepository](index.md).[getByAliasOrNull](../get-by-alias-or-null.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji](../-emoji/index.md)?<br>Retrieves the [Emoji](../-emoji/index.md) with the provided [alias](../get-by-alias-or-null.md) or null if there is no [Emoji](../-emoji/index.md) with the provided [alias](../get-by-alias-or-null.md). |
| [getByNameOrNull](../get-by-name-or-null.md) | [common]<br>suspend fun [EmojiRepository](index.md).[getByNameOrNull](../get-by-name-or-null.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji](../-emoji/index.md)?<br>Retrieves the [Emoji](../-emoji/index.md) with the provided [name](../get-by-name-or-null.md) or null if there is no [Emoji](../-emoji/index.md) with the provided [name](../get-by-name-or-null.md). |
| [getCategoryByNameOrNull](../get-category-by-name-or-null.md) | [common]<br>suspend fun [EmojiRepository](index.md).[getCategoryByNameOrNull](../get-category-by-name-or-null.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [EmojiCategory](../-emoji-category/index.md)?<br>Retrieves the [EmojiCategory](../-emoji-category/index.md) with the provided [name](../get-category-by-name-or-null.md) or null if there is no [EmojiCategory](../-emoji-category/index.md) whose [EmojiCategory.name](../-emoji-category/name.md) value matches the provided [name](../get-category-by-name-or-null.md). |
