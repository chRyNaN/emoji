package com.chrynan.emoji.presentation.android.viewmodel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmojiCategoryListItemViewModel(
    @SerialName(value = "category") val category: CharSequence,
    @SerialName(value = "category_emoji") val categoryEmoji: EmojiViewModel? = null,
    @SerialName(value = "emojis") val emojis: List<EmojiViewModel> = emptyList(),
    @SerialName(value = "is_selected") val isSelected: Boolean = false,
    @SerialName(value = "show_emoji_as_title") val showEmojiAsTitle: Boolean = false
) : ListItemViewModel {

    override val uniqueId: Long = hashCode().toLong()
}
