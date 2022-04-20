//[emoji-core](../../index.md)/[com.chrynan.emoji.core](index.md)/[asEmojiShortCode](as-emoji-short-code.md)

# asEmojiShortCode

[common]\
fun [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html).[asEmojiShortCode](as-emoji-short-code.md)(lookupChar: [Char](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char/index.html) = Emoji.DEFAULT_SHORTCODE_CHAR, allowDuplicateLookupChars: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)

Converts this [CharSequence](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-char-sequence/index.html) shortcode to a value that is useful for quick lookup. The resulting [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) is equivalent to "$lookupChar$this$lookupChar".

## Parameters

common

| | |
|---|---|
| lookupChar | The prefix and suffix character used in the resulting [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)s. |
| allowDuplicateLookupChars | Whether to allow the [lookupChar](as-emoji-short-code.md) to have consecutive duplicates in the prefix and suffix. If this value is true, then no check will be performed to see if the alias already begins or ends with the [lookupChar](as-emoji-short-code.md) and it will just be appended to the start and end of the alias. If this value is false, then the alias will be checked if it already starts or ends with the [lookupChar](as-emoji-short-code.md), if it does, the additional [lookupChar](as-emoji-short-code.md) will not be appended. |
