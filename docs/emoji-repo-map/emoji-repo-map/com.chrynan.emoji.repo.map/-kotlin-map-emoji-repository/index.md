//[emoji-repo-map](../../../index.md)/[com.chrynan.emoji.repo.map](../index.md)/[KotlinMapEmojiRepository](index.md)

# KotlinMapEmojiRepository

[common]\
class [KotlinMapEmojiRepository](index.md) : [EmojiRepository](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji-repository/index.md), [EmojiMutableRepository](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji-mutable-repository/index.md)

An implementation of [EmojiRepository](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji-repository/index.md) that uses a Kotlin [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html) for the data set.

Note that before using this repository for the first time, the KotlinMapEmojiRepository.init() function should be called. The KotlinMapEmojiRepository.init() function sets up the initial data set by loading all of the [Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md)s into the map.

## Constructors

| | |
|---|---|
| [KotlinMapEmojiRepository](-kotlin-map-emoji-repository.md) | [common]<br>fun [KotlinMapEmojiRepository](-kotlin-map-emoji-repository.md)() |

## Functions

| Name | Summary |
|---|---|
| [delete](delete.md) | [common]<br>open suspend override fun [delete](delete.md)(emoji: [Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md)) |
| [deleteAll](delete-all.md) | [common]<br>open suspend override fun [deleteAll](delete-all.md)() |
| [getAll](get-all.md) | [common]<br>open suspend override fun [getAll](get-all.md)(): [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)&lt;[Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md)&gt; |
| [getAllCategoryNames](index.md#25073561%2FFunctions%2F-812764894) | [common]<br>open suspend fun [getAllCategoryNames](index.md#25073561%2FFunctions%2F-812764894)(): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?&gt; |
| [getByAlias](get-by-alias.md) | [common]<br>open suspend override fun [getByAlias](get-by-alias.md)(alias: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md) |
| [getByName](get-by-name.md) | [common]<br>open suspend override fun [getByName](get-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md) |
| [getCategoryByName](index.md#1615706689%2FFunctions%2F-812764894) | [common]<br>open suspend fun [getCategoryByName](index.md#1615706689%2FFunctions%2F-812764894)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [EmojiCategory](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji-category/index.md) |
| [insert](insert.md) | [common]<br>open suspend override fun [insert](insert.md)(emoji: [Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md)) |
| [paginateAll](paginate-all.md) | [common]<br>open suspend override fun [paginateAll](paginate-all.md)(): PaginateRepository&lt;[Emoji.Key](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/-key/index.md), [Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md)&gt; |
| [paginateAllFromCategoryByName](index.md#1757682531%2FFunctions%2F-812764894) | [common]<br>open suspend fun [paginateAllFromCategoryByName](index.md#1757682531%2FFunctions%2F-812764894)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): PaginateRepository&lt;[Emoji.Key](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/-key/index.md), [Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md)&gt; |
| [search](search.md) | [common]<br>open suspend override fun [search](search.md)(query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md)&gt; |
| [update](update.md) | [common]<br>open suspend override fun [update](update.md)(emoji: [Emoji](../../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md)) |

## Extensions

| Name | Summary |
|---|---|
| [init](../init.md) | [common]<br>suspend fun [KotlinMapEmojiRepository](index.md).[init](../init.md)()<br>Initializes this [KotlinMapEmojiRepository](index.md) with the initial data set. |
