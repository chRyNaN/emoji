@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import android.content.Context
import android.view.View
import android.widget.TextView
import coil.request.ImageRequest
import coil.request.ImageResult
import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.core.shortCodeAliases
import com.chrynan.emoji.core.shortCodeName
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel

/**
 * Converts this [CharSequence] into an [Emoji] text [CharSequence] for the provided
 * [emojiViewModels].
 *
 * @param [emojiViewModels] The [List] of [EmojiViewModel]s to display in the resulting
 * [CharSequence].
 *
 * @param [viewToUpdate] The Android [View] to call [View.invalidate] on when the [Emoji]s have
 * finished rendering.
 *
 * @param [lookupChar] The lookup character used for short codes.
 *
 * @param [allowDuplicateLookupChars] Whether to allow duplicat consecutive [lookupChar]s.
 *
 * @return The [CharSequence] containing the [Emoji] glyphs.
 *
 * @see [emojify]
 */
fun CharSequence.emojify(
    emojiViewModels: List<EmojiViewModel>,
    viewToUpdate: View,
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
): CharSequence =
    emojify(
        emojiViewModels = emojiViewModels,
        lookupChar = lookupChar,
        context = viewToUpdate.context,
        allowDuplicateLookupChars = allowDuplicateLookupChars,
        onImageLoadingSuccess = { _, _ -> viewToUpdate.invalidate() },
        onImageLoadingError = { _, _ -> viewToUpdate.invalidate() }
    )

/**
 * Converts this [CharSequence] into an [Emoji] text [CharSequence] for the provided
 * [emojiViewModels].
 *
 * @param [emojiViewModels] The [List] of [EmojiViewModel]s to display in the resulting
 * [CharSequence].
 *
 * @param [onImageLoadingError] The callback function that is invoked when an error loading an
 * [Emoji] image is encountered. Note that not all [Emoji] glyphs use images.
 *
 * @param [onImageLoadingSuccess] The callback function that is invoked when an [Emoji] image is
 * successfully rendered. Note that not all [Emoji] glyphs use images.
 *
 * @param [lookupChar] The lookup character used for short codes.
 *
 * @param [allowDuplicateLookupChars] Whether to allow duplicat consecutive [lookupChar]s.
 *
 * @return The [CharSequence] containing the [Emoji] glyphs.
 */
inline fun CharSequence.emojify(
    emojiViewModels: List<EmojiViewModel>,
    context: Context,
    crossinline onImageLoadingError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onImageLoadingSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> },
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
): CharSequence {
    var source = this

    emojiViewModels.forEach { emojiViewModel ->
        val shortCodes = lookupChar?.let {
            emojiViewModel.emoji.shortCodeAliases(
                lookupChar = lookupChar,
                allowDuplicateLookupChars = allowDuplicateLookupChars
            ) + emojiViewModel.emoji.shortCodeName(
                lookupChar = lookupChar,
                allowDuplicateLookupChars = allowDuplicateLookupChars
            )
        } ?: emojiViewModel.emoji.aliases + emojiViewModel.emoji.name

        source = renderEmojiToCharSequenceSource(
            source = source,
            context = context,
            shortCodes = shortCodes,
            emojiViewModel = emojiViewModel,
            onImageLoadSuccess = onImageLoadingSuccess,
            onImageLoadError = onImageLoadingError
        )
    }

    return source
}

/**
 * A convenience function for calling [emojify] with [this] [TextView] as the view to update.
 *
 * @param [emojiViewModels] The [List] of [EmojiViewModel]s to display in the resulting
 * [CharSequence].
 *
 * @param [lookupChar] The lookup character used for short codes.
 *
 * @param [allowDuplicateLookupChars] Whether to allow duplicat consecutive [lookupChar]s.
 *
 * @see [emojify]
 */
fun TextView.emojify(
    emojis: List<EmojiViewModel>,
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
) {
    text = text.emojify(
        emojiViewModels = emojis,
        context = context,
        onImageLoadingError = { _, _ -> invalidate() },
        onImageLoadingSuccess = { _, _ -> invalidate() },
        lookupChar = lookupChar,
        allowDuplicateLookupChars = allowDuplicateLookupChars
    )
}
