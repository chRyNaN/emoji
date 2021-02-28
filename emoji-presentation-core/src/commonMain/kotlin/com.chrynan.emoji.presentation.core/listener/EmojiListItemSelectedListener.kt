package com.chrynan.emoji.presentation.core.listener

import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel

interface EmojiListItemSelectedListener {

    fun onEmojiListItemSelected(item: EmojiViewModel)
}

operator fun EmojiListItemSelectedListener.invoke(item: EmojiViewModel) = onEmojiListItemSelected(item = item)
