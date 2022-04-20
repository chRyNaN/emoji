//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiRepository](index.md)/[paginateAllFromCategoryByName](paginate-all-from-category-by-name.md)

# paginateAllFromCategoryByName

[common]\
open suspend fun [paginateAllFromCategoryByName](paginate-all-from-category-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): PaginateRepository&lt;[Emoji.Key](../-emoji/-key/index.md), [Emoji](../-emoji/index.md)&gt;

Retrieves a PaginateRepository to paginate through all of the available [Emoji](../-emoji/index.md)s for the [EmojiCategory](../-emoji-category/index.md) with the provided [name](paginate-all-from-category-by-name.md).

Note that the default implementation of this function simply returns a BasePaginateSource whose BasePaginateSource.fetch function returns a PagedResult from calling [getCategoryByName](get-category-by-name.md) and returning the [allEmojisFromCategories](../all-emojis-from-categories.md) on the returned [EmojiCategory](../-emoji-category/index.md). Implementations of this [EmojiRepository](index.md) can provide more efficient implementations of this function.
