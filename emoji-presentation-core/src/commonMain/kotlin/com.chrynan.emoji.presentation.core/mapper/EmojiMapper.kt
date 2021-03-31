@file:Suppress("unused")

package com.chrynan.emoji.presentation.core.mapper

import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import com.chrynan.emoji.presentation.core.viewmodel.toViewModel

/**
 * An interface that provides the ability to convert an [Emoji] to an [EmojiViewModel]. This could be useful for
 * delegating the conversion process when creating a component that retrieves [Emoji]s and needs to know how to convert
 * them to [EmojiViewModel]s.
 */
interface EmojiMapper {

    /**
     * Converts the provided [emoji] to an [EmojiViewModel]. This defaults to calling the [Emoji.toViewModel] function.
     */
    suspend fun map(emoji: Emoji): EmojiViewModel = emoji.toViewModel()
}

/**
 * A convenience function to create an [EmojiMapper] using the provided [block] or the default [EmojiMapper.map]
 * function if [block] is null.
 */
fun EmojiMapper(block: (suspend (Emoji) -> EmojiViewModel)? = null): EmojiMapper = object :
    EmojiMapper {

    override suspend fun map(emoji: Emoji): EmojiViewModel =
        block?.invoke(emoji) ?: super.map(emoji)
}
