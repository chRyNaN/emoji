//[emoji-core](../../../index.md)/[com.chrynan.emoji.core](../index.md)/[Emoji](index.md)

# Emoji

[common]\
sealed class [Emoji](index.md)

A sealed class that represents an Emoji. An implementation of this [Emoji](index.md) class should be able to be rendered to the UI to show the User a visual representation of the Emoji. Implementations of this [Emoji](index.md) class include [Emoji.Unicode](-unicode/index.md), which represents a standard Emoji unicode sequence, and [Emoji.Custom](-custom/index.md), which represents a custom Emoji defined by a URI value.

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [common]<br>object [Companion](-companion/index.md) |
| [Custom](-custom/index.md) | [common]<br>data class [Custom](-custom/index.md) : [Emoji](index.md)<br>Represents a Custom [Emoji](index.md) and all of it's related data. A Custom [Emoji](index.md) does not have a unicode value or character representing the [Emoji](index.md). Instead it is represented by a [uri](-custom/uri.md) value that points to a resource that illustrates the [Emoji](index.md). |
| [Key](-key/index.md) | [common]<br>data class [Key](-key/index.md)(typeName: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), variant: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?, id: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html))<br>Represents a key identifier for an [Emoji](index.md). The [typeName](-key/type-name.md), [name](-key/name.md), [id](-key/id.md), and [variant](-key/variant.md) values should be enough to uniquely identify an [Emoji](index.md) within a collection of values. |
| [Unicode](-unicode/index.md) | [common]<br>data class [Unicode](-unicode/index.md) : [Emoji](index.md)<br>Represents a Unicode [Emoji](index.md) and all of it's related data. |

## Properties

| Name | Summary |
|---|---|
| [aliases](aliases.md) | [common]<br>abstract val [aliases](aliases.md): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Extra names used to identify this particular [Emoji](index.md). This provides a way for multiple and alternate names to be used for a single [Emoji](index.md). |
| [category](category.md) | [common]<br>abstract val [category](category.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The optional name of the category this emoji belongs to. This is useful for partitioning [Emoji](index.md)s. |
| [group](group.md) | [common]<br>abstract val [group](group.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The optional name of the group this emoji belongs to within the [category](category.md). This is useful for fine grained partitioning of [Emoji](index.md)s. |
| [key](key.md) | [common]<br>abstract val [key](key.md): [Emoji.Key](-key/index.md)<br>A unique key identifier for this [Emoji](index.md). This value could be used to uniquely identify [Emoji](index.md)s within a [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html). |
| [name](name.md) | [common]<br>abstract val [name](name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>The name used to identify this particular [Emoji](index.md). This value should be without the lookup character prefix and suffix (:name:), and should just contain the name [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value. However, there are ways for this property to be used correctly even if the lookup character is included in the prefix and suffix. |
| [typeName](type-name.md) | [common]<br>abstract val [typeName](type-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>An identifier value that distinguishes between the different [Emoji](index.md) implementation types. For example, a value of [Emoji.Unicode.TYPE_NAME](-unicode/-companion/-t-y-p-e_-n-a-m-e.md) means the the [Emoji](index.md) should be an instance of [Emoji.Unicode](-unicode/index.md). |
| [variant](variant.md) | [common]<br>abstract val [variant](variant.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?<br>The name of the variant of the core [Emoji](index.md). This is useful when there are multiple of the same [Emoji](index.md) but with slight deviations. For instance, some hand gesture [Emoji](index.md)s have variants for different skin tones. They are the same [Emoji](index.md) but with slight deviations. Note that not all [Emoji](index.md)s have variants, and as such, this property is optional. |

## Inheritors

| Name |
|---|
| [Unicode](-unicode/index.md) |
| [Custom](-custom/index.md) |

## Extensions

| Name | Summary |
|---|---|
| [contains](../contains.md) | [common]<br>operator fun [Emoji](index.md).[contains](../contains.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determines whether this [Emoji.name](name.md) or any [Emoji.aliases](aliases.md) contains the provided [name](../contains.md).<br>[common]<br>fun [Emoji](index.md).[contains](../contains.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ignoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determines whether this [Emoji.name](name.md) or any [Emoji.aliases](aliases.md) contains the provided [name](../contains.md) using the provided [ignoreCase](../contains.md) value. |
| [containsShortCode](../contains-short-code.md) | [common]<br>fun [Emoji](index.md).[containsShortCode](../contains-short-code.md)(shortCode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ignoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, lookupChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) = Emoji.DEFAULT_SHORTCODE_CHAR, allowDuplicateLookupChars: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Determines whether this [Emoji.shortCodeName](../short-code-name.md) or any [Emoji.shortCodeAliases](../short-code-aliases.md) contains the provided [shortCode](../contains-short-code.md) using the provided [ignoreCase](../contains-short-code.md) and [allowDuplicateLookupChars](../contains-short-code.md) values. |
| [defaultAccessibilityName](../default-accessibility-name.md) | [common]<br>val [Emoji](index.md).[defaultAccessibilityName](../default-accessibility-name.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>A name for this [Emoji](index.md) that is useful for accessibility services. |
| [shortCodeAliases](../short-code-aliases.md) | [common]<br>fun [Emoji](index.md).[shortCodeAliases](../short-code-aliases.md)(lookupChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) = Emoji.DEFAULT_SHORTCODE_CHAR, allowDuplicateLookupChars: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;<br>Converts these [Emoji.aliases](aliases.md) to aliases that are useful for quick lookup. The resulting [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)s are equivalent to "$lookupChar$alias$lookupChar". |
| [shortCodeName](../short-code-name.md) | [common]<br>fun [Emoji](index.md).[shortCodeName](../short-code-name.md)(lookupChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) = Emoji.DEFAULT_SHORTCODE_CHAR, allowDuplicateLookupChars: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Converts this [Emoji.name](name.md) to a name that is useful for quick lookup. The resulting [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) is equivalent to "$lookupChar$name$lookupChar". |
