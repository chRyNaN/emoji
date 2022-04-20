//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiRepository](index.md)/[search](search.md)

# search

[common]\
abstract suspend fun [search](search.md)(query: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Emoji](../-emoji/index.md)&gt;

Searches for [Emoji](../-emoji/index.md)s by the provided [query](search.md) value. At the very least, this function should search for an [Emoji](../-emoji/index.md) with an [Emoji.name](../-emoji/name.md) value equal to [query](search.md) and any [Emoji.aliases](../-emoji/aliases.md) equal to [query](search.md).

Note that this returns a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [Emoji](../-emoji/index.md)s because it may be possible that the query matches multiple [Emoji](../-emoji/index.md)s, such as, matching the name of one [Emoji](../-emoji/index.md) and the alias of another.
