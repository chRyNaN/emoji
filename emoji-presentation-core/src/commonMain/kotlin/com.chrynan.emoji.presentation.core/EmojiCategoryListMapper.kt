@file:Suppress("unused")

package com.chrynan.emoji.presentation.core

import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.presentation.core.viewmodel.EmojiCategoryListItemViewModel
import com.chrynan.emoji.presentation.core.viewmodel.toViewModel

/**
 * An interface that provides the ability to convert a [List] of [Emoji]s into a [List] of
 * [EmojiCategoryListItemViewModel].
 *
 * @see [EmojiMapper]
 */
interface EmojiCategoryListMapper {

    /**
     * The title of an uncategorized [EmojiCategoryListItemViewModel] group.
     */
    val uncategorizedTitle: CharSequence

    /**
     * Converts the provide [emojis] to a [List] of sorted [EmojiCategoryListItemViewModel]s.
     */
    suspend fun map(emojis: List<Emoji>): List<EmojiCategoryListItemViewModel> =
        emojis.groupBy { it.category ?: uncategorizedTitle }
            .map {
                EmojiCategoryListItemViewModel(
                    category = it.key,
                    categoryEmoji = it.value.firstOrNull()?.toViewModel(),
                    emojis = it.value.map { it.toViewModel() }
                )
            }
}

/**
 * A convenience function for creating an [EmojiCategoryListMapper] with the provided [uncategorizedTitle] and optional
 * [block] that handles the conversion process. If the [block] parameter is null, a default will be used.
 */
fun EmojiCategoryListMapper(
    uncategorizedTitle: CharSequence,
    block: (suspend (List<Emoji>) -> List<EmojiCategoryListItemViewModel>)? = null
): EmojiCategoryListMapper =
    object : EmojiCategoryListMapper {

        override val uncategorizedTitle: CharSequence
            get() = uncategorizedTitle

        override suspend fun map(emojis: List<Emoji>): List<EmojiCategoryListItemViewModel> {
            return block?.invoke(emojis) ?: super.map(emojis)
        }
    }
