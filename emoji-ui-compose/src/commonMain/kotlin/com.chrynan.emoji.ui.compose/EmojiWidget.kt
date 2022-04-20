package com.chrynan.emoji.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chrynan.emoji.core.Emoji

@Composable
@ExperimentalComposeApi
@Suppress("unused")
fun EmojiWidget(
    emoji: Emoji,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    isIconPreferred: Boolean = false,
    isStaticUriPreferred: Boolean = false,
    unicodeContent: @Composable (emoji: Emoji.Unicode) -> Unit = {
        val iconUri = it.iconUri

        if (isIconPreferred && iconUri != null) {
            AsyncImage(uri = iconUri, modifier = modifier)
        } else {
            Text(text = it.character, modifier = modifier)
        }
    },
    customContent: @Composable (emoji: Emoji.Custom) -> Unit = {
        val uri = it.staticUri?.let { uri -> if (isStaticUriPreferred) uri else it.uri } ?: it.uri

        AsyncImage(uri = uri, modifier = modifier)
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
