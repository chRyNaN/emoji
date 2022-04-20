//[emoji-core](../../index.md)/[com.chrynan.emoji.core](index.md)/[containsShortCode](contains-short-code.md)

# containsShortCode

[common]\
fun [Emoji](-emoji/index.md).[containsShortCode](contains-short-code.md)(shortCode: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), ignoreCase: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, lookupChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) = Emoji.DEFAULT_SHORTCODE_CHAR, allowDuplicateLookupChars: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)

Determines whether this [Emoji.shortCodeName](short-code-name.md) or any [Emoji.shortCodeAliases](short-code-aliases.md) contains the provided [shortCode](contains-short-code.md) using the provided [ignoreCase](contains-short-code.md) and [allowDuplicateLookupChars](contains-short-code.md) values.
