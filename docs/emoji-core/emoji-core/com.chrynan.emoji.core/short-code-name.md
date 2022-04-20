//[emoji-core](../../index.md)/[com.chrynan.emoji.core](index.md)/[shortCodeName](short-code-name.md)

# shortCodeName

[common]\
fun [Emoji](-emoji/index.md).[shortCodeName](short-code-name.md)(lookupChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) = Emoji.DEFAULT_SHORTCODE_CHAR, allowDuplicateLookupChars: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Converts this [Emoji.name](-emoji/name.md) to a name that is useful for quick lookup. The resulting [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) is equivalent to "$lookupChar$name$lookupChar".

## Parameters

common

| | |
|---|---|
| lookupChar | The prefix and suffix character used in the resulting [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html). |
| allowDuplicateLookupChars | Whether to allow the [lookupChar](short-code-name.md) to have consecutive duplicates in the prefix and suffix. If this value is true, then no check will be performed to see if the name already begins or ends with the [lookupChar](short-code-name.md) and it will just be appended to the start and end of the name. If this value is false, then the name will be checked if it already starts or ends with the [lookupChar](short-code-name.md), if it does, the additional [lookupChar](short-code-name.md) will not be appended. |
