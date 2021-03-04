@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import com.chrynan.emoji.core.shortCodeName
import com.chrynan.emoji.core.shortCodeAliases

/**
 * Pre-computes the [Emoji] text for the provided [source], [emojiViewModels], and [textView].
 *
 * Note that this does not call [TextView.invalidate] when the Emojis are rendered. This is because
 * we are not certain that this is called from the UI thread.
 *
 * @param [source] The source [CharSequence] to edit by adding in the Emojis.
 *
 * @param [emojiViewModels] The [List] of [EmojiViewModel]s that are to be rendered into the
 * [source] text.
 *
 * @param [textView] The [TextView] that will display the resulting text.
 *
 * @param [onEditEmojiText] A convenience function that can alter the Emoji text after it has been
 * rendered.
 *
 * @param [lookupChar] The lookup character used for finding the short codes.
 *
 * @param [allowDuplicateLookupChars] Whether to allow duplicate consecutive [lookupChar]s.
 *
 * @see [preComputeText]
 * @see [emojifyAsync]
 * @see [Emoji.shortCodeName]
 * @se [Emoji.shortCodeAliases]
 */
@Suppress("RedundantSuspendModifier")
suspend inline fun preComputeEmojiText(
    source: CharSequence,
    emojiViewModels: List<EmojiViewModel>,
    textView: TextView,
    crossinline onEditEmojiText: (CharSequence) -> CharSequence = { it },
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
): PrecomputedTextCompat = preComputeText(
    textView = textView,
    computeText = {
        val text = source.emojify(
            emojiViewModels = emojiViewModels,
            context = textView.context,
            lookupChar = lookupChar,
            allowDuplicateLookupChars = allowDuplicateLookupChars
        )

        onEditEmojiText(text)
    }
)

/**
 * A convenience function for calling [preComputeEmojiText] with [this] [TextView] as the provided
 * [TextView].
 *
 * @see [preComputeEmojiText]
 */
suspend inline fun TextView.preComputeEmojiText(
    source: CharSequence,
    emojiViewModels: List<EmojiViewModel>,
    crossinline onEditEmojiText: (CharSequence) -> CharSequence = { it },
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false
): PrecomputedTextCompat = preComputeEmojiText(
    source = source,
    textView = this,
    emojiViewModels = emojiViewModels,
    onEditEmojiText = onEditEmojiText,
    lookupChar = lookupChar,
    allowDuplicateLookupChars = allowDuplicateLookupChars
)

/**
 * A convenience function that calls pre-computes the [Emoji] text and then renders to the provided
 * [textView].
 *
 * @see [preComputeEmojiText]
 * @see [preComputeTextAndRender]
 */
inline fun preComputeEmojiTextAndRender(
    source: CharSequence,
    emojiViewModels: List<EmojiViewModel>,
    textView: TextView,
    crossinline onEditEmojiText: (CharSequence) -> CharSequence = { it },
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false,
    coroutineScope: CoroutineScope,
    calculateDispatcher: CoroutineDispatcher,
    renderDispatcher: CoroutineDispatcher
): Job = preComputeTextAndRender(
    textView = textView,
    computeText = {
        val text = source.emojify(
            emojiViewModels = emojiViewModels,
            context = textView.context,
            lookupChar = lookupChar,
            allowDuplicateLookupChars = allowDuplicateLookupChars
        )

        onEditEmojiText(text)
    },
    coroutineScope = coroutineScope,
    calculateDispatcher = calculateDispatcher,
    renderDispatcher = renderDispatcher
)

/**
 * A convenience function for calling [preComputeEmojiTextAndRender] with [this] [TextView] as the
 * provided [TextView].
 *
 * @see [preComputeEmojiTextAndRender]
 */
inline fun TextView.preComputeEmojiTextAndRender(
    source: CharSequence,
    emojiViewModels: List<EmojiViewModel>,
    crossinline onEditEmojiText: (CharSequence) -> CharSequence = { it },
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR,
    allowDuplicateLookupChars: Boolean = false,
    coroutineScope: CoroutineScope,
    calculateDispatcher: CoroutineDispatcher,
    renderDispatcher: CoroutineDispatcher
): Job = preComputeEmojiTextAndRender(
    source = source,
    textView = this,
    emojiViewModels = emojiViewModels,
    onEditEmojiText = onEditEmojiText,
    lookupChar = lookupChar,
    allowDuplicateLookupChars = allowDuplicateLookupChars,
    coroutineScope = coroutineScope,
    calculateDispatcher = calculateDispatcher,
    renderDispatcher = renderDispatcher
)
