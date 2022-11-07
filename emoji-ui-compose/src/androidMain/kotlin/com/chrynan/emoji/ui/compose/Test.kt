package com.chrynan.emoji.ui.compose

import androidx.compose.runtime.Composable
import com.chrynan.emoji.core.Emoji

@Composable
@Preview
fun Test() {
    EmojiWidget(emoji = Emoji(name = "", character = "", unicodeString = ""))
}
