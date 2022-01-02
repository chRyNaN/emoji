package com.chrynan.emoji.presentation.android.listener

import com.chrynan.emoji.presentation.core.viewmodel.EmojiChipViewModel

interface EmojiChipGroupListener {

    fun onChipSelected(emojiChipViewModel: EmojiChipViewModel)

    fun onAddEmojiSelected()
}
