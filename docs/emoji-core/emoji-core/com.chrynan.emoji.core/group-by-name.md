//[emoji-core](../../index.md)/[com.chrynan.emoji.core](index.md)/[groupByName](group-by-name.md)

# groupByName

[common]\
fun [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[Emoji](-emoji/index.md)&gt;.[groupByName](group-by-name.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EmojiGroup](-emoji-group/index.md)&gt;

Converts this [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html) of [Emoji](-emoji/index.md)s into a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [EmojiGroup](-emoji-group/index.md)s that are derived by the [Emoji.name](-emoji/name.md) property instead of the [Emoji.group](-emoji/group.md) property. This allows [Emoji](-emoji/index.md)s with the same [Emoji.name](-emoji/name.md) to be grouped together which may possibly be the same [Emoji](-emoji/index.md) but with different [Emoji.variant](-emoji/variant.md)s.

[common]\
fun &lt;[K](group-by-name.md)&gt; [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)&lt;[K](group-by-name.md), [Emoji](-emoji/index.md)&gt;.[groupByName](group-by-name.md)(): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[EmojiGroup](-emoji-group/index.md)&gt;

Converts this [Map](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html) of [Emoji](-emoji/index.md) values into a [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of [EmojiGroup](-emoji-group/index.md)s that are derived by the [Emoji.name](-emoji/name.md) property instead of the [Emoji.group](-emoji/group.md) property. This allows [Emoji](-emoji/index.md)s with the same [Emoji.name](-emoji/name.md) to be grouped together which may possibly be the same [Emoji](-emoji/index.md) but with different [Emoji.variant](-emoji/variant.md)s.
