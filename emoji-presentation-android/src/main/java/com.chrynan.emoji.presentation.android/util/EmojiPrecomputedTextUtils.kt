@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

@Suppress("RedundantSuspendModifier")
suspend inline fun precomputeEmojiText(
    charSequence: CharSequence,
    emojis: List<EmojiViewModel>,
    textView: TextView,
    crossinline onEmojiTextCreated: (CharSequence) -> CharSequence = { it },
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR
): PrecomputedTextCompat {
    val params = TextViewCompat.getTextMetricsParams(textView)

    val text = charSequence.emojify(
        emojis = emojis,
        context = textView.context,
        lookupChar = lookupChar
    )

    val resultText = onEmojiTextCreated(text)

    return PrecomputedTextCompat.create(resultText, params)
}

@Suppress("RedundantSuspendModifier")
suspend inline fun precomputeEmojiText(
    charSequence: CharSequence,
    shortcodeEmojiMap: Map<String, EmojiViewModel>,
    textView: TextView,
    crossinline onEmojiTextCreated: (CharSequence) -> CharSequence = { it }
): PrecomputedTextCompat {
    val params = TextViewCompat.getTextMetricsParams(textView)

    val text = charSequence.emojifyAll(
        shortcodeEmojiMap = shortcodeEmojiMap,
        context = textView.context
    )

    val resultText = onEmojiTextCreated(text)

    return PrecomputedTextCompat.create(resultText, params)
}

inline fun TextView.emojifyAsync(
    charSequence: CharSequence,
    emojis: List<EmojiViewModel>,
    coroutineScope: CoroutineScope,
    calculateDispatcher: CoroutineDispatcher,
    renderDispatcher: CoroutineDispatcher,
    crossinline onEmojiTextCreated: (CharSequence) -> CharSequence = { it },
    lookupChar: Char? = Emoji.DEFAULT_SHORTCODE_CHAR
): Job {
    val ref = WeakReference(this)

    return coroutineScope.launch(calculateDispatcher) {
        val text = precomputeEmojiText(
            charSequence = charSequence,
            emojis = emojis,
            textView = this@emojifyAsync,
            onEmojiTextCreated = onEmojiTextCreated,
            lookupChar = lookupChar
        )

        coroutineScope.launch(renderDispatcher) {
            ref.get()?.let { textView ->
                TextViewCompat.setPrecomputedText(textView, text)
            }
        }
    }
}

inline fun TextView.emojifyAsync(
    charSequence: CharSequence,
    shortcodeEmojiMap: Map<String, EmojiViewModel>,
    coroutineScope: CoroutineScope,
    calculateDispatcher: CoroutineDispatcher,
    renderDispatcher: CoroutineDispatcher,
    crossinline onEmojiTextCreated: (CharSequence) -> CharSequence = { it }
): Job {
    val ref = WeakReference(this)

    return coroutineScope.launch(calculateDispatcher) {
        val text = precomputeEmojiText(
            charSequence = charSequence,
            shortcodeEmojiMap = shortcodeEmojiMap,
            textView = this@emojifyAsync,
            onEmojiTextCreated = onEmojiTextCreated
        )

        coroutineScope.launch(renderDispatcher) {
            ref.get()?.let { textView ->
                TextViewCompat.setPrecomputedText(textView, text)
            }
        }
    }
}
