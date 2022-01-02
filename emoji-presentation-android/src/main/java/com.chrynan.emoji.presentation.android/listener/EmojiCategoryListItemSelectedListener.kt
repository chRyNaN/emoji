package com.chrynan.emoji.presentation.android.listener

import com.chrynan.emoji.presentation.android.viewmodel.EmojiCategoryListItemViewModel

fun interface EmojiCategoryListItemSelectedListener {

    fun onEmojiCategoryListItemSelected(item: EmojiCategoryListItemViewModel)
}

operator fun EmojiCategoryListItemSelectedListener.invoke(item: EmojiCategoryListItemViewModel) =
    onEmojiCategoryListItemSelected(item = item)
