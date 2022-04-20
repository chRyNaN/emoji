//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiRepository](index.md)/[getAllCategoryNames](get-all-category-names.md)

# getAllCategoryNames

[common]\
open suspend fun [getAllCategoryNames](get-all-category-names.md)(): [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?&gt;

Retrieves all of the known [EmojiCategory](../-emoji-category/index.md) names represented by this [EmojiRepository](index.md).

Note that the default implementation performs a [getAll](get-all.md), then calls [categorize](../categorize.md) on the resulting items, and [Collection.map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/index.html)s them to a [Set](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html) of unique [EmojiCategory](../-emoji-category/index.md) names. Implementations of [EmojiRepository](index.md) can provide more efficient implementations of this function.
