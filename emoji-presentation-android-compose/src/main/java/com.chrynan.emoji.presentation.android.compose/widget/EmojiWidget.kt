package com.chrynan.emoji.presentation.android.compose.widget

import androidx.compose.runtime.Composable
import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel

@Composable
fun EmojiWidget(
    viewModel: EmojiViewModel
) {
    when (val emoji = viewModel.emoji) {
        is Emoji.Unicode -> UnicodeEmojiWidget(viewModel = viewModel, emoji = emoji)
        is Emoji.Custom -> CustomEmojiWidget(viewModel = viewModel, emoji = emoji)
    }
}

@Composable
internal fun UnicodeEmojiWidget(
    viewModel: EmojiViewModel,
    emoji: Emoji.Unicode
) {
    if (viewModel.isIconPreferred && emoji.iconUri != null) {

    } else {

    }
}

@Composable
internal fun CustomEmojiWidget(
    viewModel: EmojiViewModel,
    emoji: Emoji.Custom
) {
    if (viewModel.isStaticUriPreferred && emoji.staticUri != null) {

    } else {

    }
}
