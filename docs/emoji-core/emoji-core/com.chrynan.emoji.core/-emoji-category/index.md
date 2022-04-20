//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[EmojiCategory](index.md)

# EmojiCategory

[common]\
data class [EmojiCategory](index.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, groups: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EmojiGroup](../-emoji-group/index.md)&gt;, icon: [Emoji](../-emoji/index.md)?)

Represents a category, or collection of [EmojiGroup](../-emoji-group/index.md)s.

## Constructors

| | |
|---|---|
| [EmojiCategory](-emoji-category.md) | [common]<br>fun [EmojiCategory](-emoji-category.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, groups: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EmojiGroup](../-emoji-group/index.md)&gt;, icon: [Emoji](../-emoji/index.md)? = null) |

## Properties

| Name | Summary |
|---|---|
| [groups](groups.md) | [common]<br>val [groups](groups.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EmojiGroup](../-emoji-group/index.md)&gt;<br>The list of [EmojiGroup](../-emoji-group/index.md)s within this category. |
| [icon](icon.md) | [common]<br>val [icon](icon.md): [Emoji](../-emoji/index.md)? = null<br>An optional [Emoji](../-emoji/index.md) acting as an icon for this category. |
| [name](name.md) | [common]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The name of this category. |

## Extensions

| Name | Summary |
|---|---|
| [allEmojis](../all-emojis.md) | [common]<br>fun [EmojiCategory](index.md).[allEmojis](../all-emojis.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[Emoji](../-emoji/index.md)&gt;<br>Retrieves a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of all of the [Emoji](../-emoji/index.md)s belonging to all of the [EmojiGroup](../-emoji-group/index.md)s in this [EmojiCategory](index.md). |
