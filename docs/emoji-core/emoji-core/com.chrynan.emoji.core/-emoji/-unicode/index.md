//[emoji-core](../../../../index.md)/[com.chrynan.emoji.core](../../index.md)/[Emoji](../index.md)/[Unicode](index.md)

# Unicode

[common]\
data class [Unicode](index.md) : [Emoji](../index.md)

Represents a Unicode [Emoji](../index.md) and all of it's related data.

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [aliases](aliases.md) | [common]<br>open override val [aliases](aliases.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Extra names used to identify this particular [Emoji](../index.md). This provides a way for multiple and alternate names to be used for a single [Emoji](../index.md). |
| [category](category.md) | [common]<br>open override val [category](category.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The optional name of the category this emoji belongs to. This is useful for partitioning [Emoji](../index.md)s. |
| [character](character.md) | [common]<br>val [character](character.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [group](group.md) | [common]<br>open override val [group](group.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The optional name of the group this emoji belongs to within the [category](category.md). This is useful for fine grained partitioning of [Emoji](../index.md)s. |
| [iconUri](icon-uri.md) | [common]<br>val [iconUri](icon-uri.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>An optional URI to an image representation of this emoji. |
| [key](key.md) | [common]<br>open override val [key](key.md): [Emoji.Key](../-key/index.md)<br>A unique key identifier for this [Emoji](../index.md). This value could be used to uniquely identify [Emoji](../index.md)s within a [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html). |
| [name](name.md) | [common]<br>open override val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name used to identify this particular [Emoji](../index.md). This value should be without the lookup character prefix and suffix (:name:), and should just contain the name [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value. However, there are ways for this property to be used correctly even if the lookup character is included in the prefix and suffix. |
| [typeName](type-name.md) | [common]<br>open override val [typeName](type-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>An identifier value that distinguishes between the different [Emoji](../index.md) implementation types. For example, a value of [Emoji.Unicode.TYPE_NAME](-companion/-t-y-p-e_-n-a-m-e.md) means the the [Emoji](../index.md) should be an instance of [Emoji.Unicode](index.md). |
| [unicodeList](unicode-list.md) | [common]<br>val [unicodeList](unicode-list.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>The [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html) of unicode [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) values that represent this emoji (ex: listOf(U+1F600)). This is different from the [unicodeString](unicode-string.md) value, as that includes all the unicode Strings in a single [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value, whereas, this [unicodeList](unicode-list.md) contains a list of all of those unicode Strings. Note that each unicode value should be prefaced with the "U+" String. |
| [unicodeString](unicode-string.md) | [common]<br>val [unicodeString](unicode-string.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) of unicode values that represent this emoji (ex: U+1F600). Note that each unicode value should be prefaced with the "U+" String. |
| [variant](variant.md) | [common]<br>open override val [variant](variant.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The name of the variant of the core [Emoji](../index.md). This is useful when there are multiple of the same [Emoji](../index.md) but with slight deviations. For instance, some hand gesture [Emoji](../index.md)s have variants for different skin tones. They are the same [Emoji](../index.md) but with slight deviations. Note that not all [Emoji](../index.md)s have variants, and as such, this property is optional. |
