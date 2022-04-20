//[emoji-core](../../../../index.md)/[com.chrynan.emoji.core](../../index.md)/[Emoji](../index.md)/[Custom](index.md)

# Custom

[common]\
data class [Custom](index.md) : [Emoji](../index.md)

Represents a Custom [Emoji](../index.md) and all of it's related data. A Custom [Emoji](../index.md) does not have a unicode value or character representing the [Emoji](../index.md). Instead it is represented by a [uri](uri.md) value that points to a resource that illustrates the [Emoji](../index.md).

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Properties

| Name | Summary |
|---|---|
| [aliases](aliases.md) | [common]<br>open override val [aliases](aliases.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Extra names used to identify this particular [Emoji](../index.md). This provides a way for multiple and alternate names to be used for a single [Emoji](../index.md). |
| [category](category.md) | [common]<br>open override val [category](category.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The optional name of the category this emoji belongs to. This is useful for partitioning [Emoji](../index.md)s. |
| [group](group.md) | [common]<br>open override val [group](group.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The optional name of the group this emoji belongs to within the [category](category.md). This is useful for fine grained partitioning of [Emoji](../index.md)s. |
| [key](key.md) | [common]<br>open override val [key](key.md): [Emoji.Key](../-key/index.md)<br>A unique key identifier for this [Emoji](../index.md). This value could be used to uniquely identify [Emoji](../index.md)s within a [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html). |
| [mimeType](mime-type.md) | [common]<br>val [mimeType](mime-type.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The optional mime type of the image located at the [uri](uri.md) value. |
| [name](name.md) | [common]<br>open override val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name used to identify this particular [Emoji](../index.md). This value should be without the lookup character prefix and suffix (:name:), and should just contain the name [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value. However, there are ways for this property to be used correctly even if the lookup character is included in the prefix and suffix. |
| [staticUri](static-uri.md) | [common]<br>val [staticUri](static-uri.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>An optional URI value to a static version of the image resource visually representing this [Emoji](../index.md). This is useful as an alternative and regular image resource if the [uri](uri.md) value is an animated GIF. |
| [typeName](type-name.md) | [common]<br>open override val [typeName](type-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>An identifier value that distinguishes between the different [Emoji](../index.md) implementation types. For example, a value of [Emoji.Unicode.TYPE_NAME](../-unicode/-companion/-t-y-p-e_-n-a-m-e.md) means the the [Emoji](../index.md) should be an instance of [Emoji.Unicode](../-unicode/index.md). |
| [uri](uri.md) | [common]<br>val [uri](uri.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The URI value to the image resource visually representing this [Emoji](../index.md). |
| [variant](variant.md) | [common]<br>open override val [variant](variant.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The name of the variant of the core [Emoji](../index.md). This is useful when there are multiple of the same [Emoji](../index.md) but with slight deviations. For instance, some hand gesture [Emoji](../index.md)s have variants for different skin tones. They are the same [Emoji](../index.md) but with slight deviations. Note that not all [Emoji](../index.md)s have variants, and as such, this property is optional. |
