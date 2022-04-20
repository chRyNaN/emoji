//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiRepository](index.md)/[getCategoryByName](get-category-by-name.md)

# getCategoryByName

[common]\
open suspend fun [getCategoryByName](get-category-by-name.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?): [EmojiCategory](../-emoji-category/index.md)

Retrieves the [EmojiCategory](../-emoji-category/index.md) with the provided [name](get-category-by-name.md) or throws [InvalidEmojiCategoryIdentifierException](../-invalid-emoji-category-identifier-exception/index.md) if there is no [EmojiCategory](../-emoji-category/index.md) with the provided [name](get-category-by-name.md).

Note that the default implementation performs a [getAll](get-all.md), then [categorize](../categorize.md)s the resulting [Emoji](../-emoji/index.md)s, and returns the first [EmojiCategory](../-emoji-category/index.md) with the provided [name](get-category-by-name.md) or throws [InvalidEmojiCategoryIdentifierException](../-invalid-emoji-category-identifier-exception/index.md) if there is no [EmojiCategory](../-emoji-category/index.md) with the provided [name](get-category-by-name.md). Implementations of [EmojiRepository](index.md) can provide more efficient implementations of this function.
