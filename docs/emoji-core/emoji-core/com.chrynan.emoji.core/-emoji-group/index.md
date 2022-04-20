//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiGroup](index.md)

# EmojiGroup

[common]\
data class [EmojiGroup](index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, emojis: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Emoji](../-emoji/index.md)&gt;, icon: [Emoji](../-emoji/index.md)?)

Represents a group, or collection of [Emoji](../-emoji/index.md)s.

## Constructors

| | |
|---|---|
| [EmojiGroup](-emoji-group.md) | [common]<br>fun [EmojiGroup](-emoji-group.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, emojis: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Emoji](../-emoji/index.md)&gt;, icon: [Emoji](../-emoji/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [emojis](emojis.md) | [common]<br>val [emojis](emojis.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>The list of [Emoji](../-emoji/index.md)s within this group. |
| [icon](icon.md) | [common]<br>val [icon](icon.md): [Emoji](../-emoji/index.md)? = null<br>An optional [Emoji](../-emoji/index.md) acting as an icon for this group. |
| [name](name.md) | [common]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The name of this group. |
