package com.chrynan.emoji.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.chrynan.emoji.core.Emoji

@Composable
fun EmojiWidget(
    emoji: Emoji,
    modifier: Modifier = Modifier,
    isIconPreferred: Boolean = false,
    isStaticUriPreferred: Boolean = false
) {
    Box(modifier = modifier) {
        when (emoji) {
            is Emoji.Unicode -> {
                val iconUri = emoji.iconUri

                if (isIconPreferred && iconUri != null) {
                    EmojiImageWidget(uri = iconUri)
                } else {
                    EmojiTextWidget(emoji = emoji)
                }
            }
            is Emoji.Custom -> {
                val uri = emoji.staticUri?.let { if (isStaticUriPreferred) it else emoji.uri } ?: emoji.uri

                EmojiImageWidget(uri = uri)
            }
        }
    }
}

@Composable
internal fun EmojiTextWidget(
    emoji: Emoji.Unicode
) {

}

@Composable
internal fun EmojiImageWidget(
    uri: String
) {

}
