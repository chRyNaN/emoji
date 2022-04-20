//[emoji-core](../../../../../index.md)/[com.chrynan.emoji.core](../../../index.md)/[Emoji](../../index.md)/[Key](../index.md)/[Companion](index.md)/[fromString](from-string.md)

# fromString

[common]\
fun [fromString](from-string.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji.Key](../index.md)

Retrieves a [Key](../index.md) from the provided [String](from-string.md). Note that the provided [value](from-string.md) is expected to be in the format "typeName:name:id:variant". Also, note that if there is less than two colon (':') characters, this function will throw an [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html).

Note that the variant part of the formatted [String](from-string.md) is optional. It can either be omitted or a value of "null".

#### Return

The [Key](../index.md) derived from the specially formatted [String](from-string.md).

## Parameters

common

| | |
|---|---|
| value | The [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) value in the format "typeName:name:id:variant". |
