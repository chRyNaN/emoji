//[emoji-ui-compose](../../index.md)/[com.chrynan.emoji.ui.compose](index.md)/[EmojiWidget](-emoji-widget.md)

# EmojiWidget

[common]\

@Composable

fun [EmojiWidget](-emoji-widget.md)(emoji: [Emoji](../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/index.md), modifier: Modifier = Modifier, contentAlignment: Alignment = Alignment.TopStart, propagateMinConstraints: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isIconPreferred: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isStaticUriPreferred: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, unicodeContent: @Composable(emoji: [Emoji.Unicode](../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/-unicode/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        val iconUri = it.iconUri

        if (isIconPreferred &amp;&amp; iconUri != null) {
            AsyncImage(uri = iconUri, modifier = modifier)
        } else {
            Text(text = it.character, modifier = modifier)
        }
    }, customContent: @Composable(emoji: [Emoji.Custom](../../../emoji-core/emoji-core/com.chrynan.emoji.core/-emoji/-custom/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = {
        val uri = it.staticUri?.let { uri -&gt; if (isStaticUriPreferred) uri else it.uri } ?: it.uri

        AsyncImage(uri = uri, modifier = modifier)
    })
