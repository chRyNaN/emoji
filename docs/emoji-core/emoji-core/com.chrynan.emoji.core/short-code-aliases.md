//[emoji-core](../../index.md)/[com.chrynan.emoji.core](index.md)/[shortCodeAliases](short-code-aliases.md)

# shortCodeAliases

[common]\
fun [Emoji](-emoji/index.md).[shortCodeAliases](short-code-aliases.md)(lookupChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) = Emoji.DEFAULT_SHORTCODE_CHAR, allowDuplicateLookupChars: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;

Converts these [Emoji.aliases](-emoji/aliases.md) to aliases that are useful for quick lookup. The resulting [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)s are equivalent to "$lookupChar$alias$lookupChar".

## Parameters

common

| | |
|---|---|
| lookupChar | The prefix and suffix character used in the resulting [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)s. |
| allowDuplicateLookupChars | Whether to allow the [lookupChar](short-code-aliases.md) to have consecutive duplicates in the prefix and suffix. If this value is true, then no check will be performed to see if the alias already begins or ends with the [lookupChar](short-code-aliases.md) and it will just be appended to the start and end of the alias. If this value is false, then the alias will be checked if it already starts or ends with the [lookupChar](short-code-aliases.md), if it does, the additional [lookupChar](short-code-aliases.md) will not be appended. |
