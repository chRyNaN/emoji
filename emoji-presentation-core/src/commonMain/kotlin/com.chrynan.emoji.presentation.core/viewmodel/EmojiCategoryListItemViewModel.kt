package com.chrynan.emoji.presentation.core.viewmodel

data class EmojiCategoryListItemViewModel(
    val category: CharSequence,
    val categoryEmoji: EmojiViewModel? = null,
    val emojis: List<EmojiViewModel> = emptyList(),
    val isSelected: Boolean = false,
    val showEmojiAsTitle: Boolean = false
) : ListItemViewModel {

    override val uniqueId: Long = hashCode().toLong()
}
