package com.chrynan.emoji.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chrynan.emoji.core.Emoji
import com.chrynan.ui.components.image.AsyncImage

@Composable
@Suppress("unused")
fun EmojiWidget(
    emoji: Emoji,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    propagateMinConstraints: Boolean = false,
    isIconPreferred: Boolean = false,
    isStaticUriPreferred: Boolean = false,
    unicodeContent: @Composable BoxScope.(emoji: Emoji.Unicode) -> Unit = {
        val iconUri = it.iconUri

        if (isIconPreferred && iconUri != null) {
            AsyncImage(data = iconUri, modifier = Modifier.matchParentSize())
        } else {
            Text(text = it.character, modifier = Modifier.matchParentSize())
        }
    },
    customContent: @Composable BoxScope.(emoji: Emoji.Custom) -> Unit = {
        val uri = it.staticUri?.let { uri -> if (isStaticUriPreferred) uri else it.uri } ?: it.uri

        AsyncImage(data = uri, modifier = Modifier.matchParentSize())
    }
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints
    ) {
        when (emoji) {
            is Emoji.Unicode -> unicodeContent(emoji)
            is Emoji.Custom -> customContent(emoji)
        }
    }
}
