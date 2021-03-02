package com.chrynan.emoji.presentation.core.listener

import com.chrynan.emoji.presentation.core.viewmodel.EmojiCategoryListItemViewModel

fun interface EmojiCategoryListItemSelectedListener {

    fun onEmojiCategoryListItemSelected(item: EmojiCategoryListItemViewModel)
}

operator fun EmojiCategoryListItemSelectedListener.invoke(item: EmojiCategoryListItemViewModel) =
    onEmojiCategoryListItemSelected(item = item)
