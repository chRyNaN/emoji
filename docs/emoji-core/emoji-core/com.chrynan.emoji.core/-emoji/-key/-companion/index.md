//[emoji-core](../../../../../index.md)/[com.chrynan.emoji.core](../../../index.md)/[Emoji](../../index.md)/[Key](../index.md)/[Companion](index.md)

# Companion

[common]\
object [Companion](index.md)

## Functions

| Name | Summary |
|---|---|
| [fromString](from-string.md) | [common]<br>fun [fromString](from-string.md)(value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Emoji.Key](../index.md)<br>Retrieves a [Key](../index.md) from the provided [String](from-string.md). Note that the provided [value](from-string.md) is expected to be in the format "typeName:name:id:variant". Also, note that if there is less than two colon (':') characters, this function will throw an [IllegalArgumentException](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-illegal-argument-exception/index.html). |
