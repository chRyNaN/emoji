//[emoji-core](../../../../index.md)/[com.chrynan.emoji.core](../../index.md)/[Emoji](../index.md)/[Key](index.md)

# Key

[common]\
data class [Key](index.md)(typeName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), variant: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))

Represents a key identifier for an [Emoji](../index.md). The [typeName](type-name.md), [name](name.md), [id](id.md), and [variant](variant.md) values should be enough to uniquely identify an [Emoji](../index.md) within a collection of values.

## Constructors

| | |
|---|---|
| [Key](-key.md) | [common]<br>fun [Key](-key.md)(typeName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), variant: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null, id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [toString](to-string.md) | [common]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [id](id.md) | [common]<br>val [id](id.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The unique identifier value for this [Emoji](../index.md). Note that different [Emoji](../index.md) implementations may provide different [id](id.md) value implementations. This is why the [typeName](type-name.md) property is important to include, so that the [id](id.md) values can be differentiated. |
| [name](name.md) | [common]<br>val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The [Emoji.name](../name.md) value of the [Emoji](../index.md) that this [Key](index.md) represents. This value is important to uniquely identify the [Emoji](../index.md) because some implementations may use the same resource for different [Emoji](../index.md)s but they still have different names. |
| [typeName](type-name.md) | [common]<br>val [typeName](type-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The [Emoji.typeName](../type-name.md) value of the [Emoji](../index.md) that this [Key](index.md) represents. This value is important to uniquely identify the [Emoji](../index.md) because different [Emoji](../index.md) implementations will have different [id](id.md) implementations and this provides a way to differentiate between them. |
| [variant](variant.md) | [common]<br>val [variant](variant.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? = null<br>The [Emoji.variant](../variant.md) value of the [Emoji](../index.md) that this [Key](index.md) represents. This value is important to uniquely identify the [Emoji](../index.md) because some [Emoji](../index.md)s have multiple variants with the same name. |
