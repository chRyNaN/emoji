//[emoji-ui-compose](../../index.md)/[com.chrynan.emoji.ui.compose](index.md)

# Package com.chrynan.emoji.ui.compose

## Functions

| Name | Summary |
|---|---|
| [EmojiWidget](-emoji-widget.md) | [common]<br>@Composable<br>fun [EmojiWidget](-emoji-widget.md)(emoji: [Emoji](../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md), modifier: Modifier = Modifier, contentAlignment: Alignment = Alignment.TopStart, propagateMinConstraints: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isIconPreferred: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isStaticUriPreferred: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, unicodeContent: @Composable(emoji: [Emoji.Unicode](../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/-unicode/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         val iconUri = it.iconUri<br>        if (isIconPreferred &amp;&amp; iconUri != null) {             AsyncImage(uri = iconUri, modifier = modifier)         } else {             Text(text = it.character, modifier = modifier)         }     }, customContent: @Composable(emoji: [Emoji.Custom](../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/-custom/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {         val uri = it.staticUri?.let { uri -&gt; if (isStaticUriPreferred) uri else it.uri } ?: it.uri<br>        AsyncImage(uri = uri, modifier = modifier)     }) |
