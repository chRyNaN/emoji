//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiUserPreferenceRepository](index.md)

# EmojiUserPreferenceRepository

[common]\
interface [EmojiUserPreferenceRepository](index.md)

A repository that retrieves [Emoji](../-emoji/index.md)s related to a User's preference and actions.

## Functions

| Name | Summary |
|---|---|
| [getAllMostUsed](get-all-most-used.md) | [common]<br>abstract suspend fun [getAllMostUsed](get-all-most-used.md)(): [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>Retrieves a [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html) of [Emoji](../-emoji/index.md)s that are most used by the current User. |
| [getAllPreferred](get-all-preferred.md) | [common]<br>abstract suspend fun [getAllPreferred](get-all-preferred.md)(): [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>Retrieves a [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html) of preferred, or favorited, [Emoji](../-emoji/index.md)s by the current User. |
| [getAllRecentlyUsed](get-all-recently-used.md) | [common]<br>abstract suspend fun [getAllRecentlyUsed](get-all-recently-used.md)(): [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>Retrieves a [Sequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence/index.html) of recently used [Emoji](../-emoji/index.md)s by the current User. |
