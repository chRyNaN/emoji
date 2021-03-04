@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import android.widget.TextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import kotlinx.coroutines.*
import java.lang.ref.WeakReference

/**
 * Creates a [PrecomputedTextCompat] from the provided [textView] and [computeText] function. This
 * is a more efficient way to render text on Android. This would be especially useful within a UI
 * list of complex spanned text.
 *
 * @param [textView] The [TextView] that this text will be rendered to. This is needed to obtain
 * the [PrecomputedTextCompat.Params] for the [TextView].
 *
 * @param [computeText] The function that obtains the [CharSequence] that will be rendered to the
 * [textView].
 *
 * @param [onBeforeComputeText] A function that is called before [computeText] is called.
 *
 * @param [onAfterComputeText] A function that is called after [computeText] is called and with the
 * resulting [CharSequence] from the [computeText] function.
 *
 * @return [PrecomputedTextCompat] The resulting [PrecomputedTextCompat] instance.
 *
 * @see [PrecomputedTextCompat]
 */
@Suppress("RedundantSuspendModifier")
suspend inline fun preComputeText(
    textView: TextView,
    computeText: () -> CharSequence,
    onBeforeComputeText: () -> Unit = {},
    onAfterComputeText: (CharSequence) -> Unit = {}
): PrecomputedTextCompat {
    val params = TextViewCompat.getTextMetricsParams(textView)

    onBeforeComputeText()

    val text = computeText()

    onAfterComputeText(text)

    return PrecomputedTextCompat.create(text, params)
}

/**
 * A convenience function for [preComputeText] where the [TextView] used is [this] [TextView].
 *
 * @see [preComputeText]
 */
@JvmName("preComputeTextForTextView")
suspend inline fun TextView.preComputeText(
    computeText: () -> CharSequence,
    onBeforeComputeText: () -> Unit = {},
    onAfterComputeText: (CharSequence) -> Unit = {}
): PrecomputedTextCompat = preComputeText(
    textView = this,
    computeText = computeText,
    onBeforeComputeText = onBeforeComputeText,
    onAfterComputeText = onAfterComputeText
)

/**
 * Pre-computes the text from the [computeText] function and renders it to the [textView]
 * asynchronously using the provided [coroutineScope], [calculateDispatcher], and
 * [renderDispatcher] parameters.
 *
 * @return [Job] The Kotlin Coroutine [Job] that is being performed that computes the text and
 * renders it.
 *
 * @see [preComputeText]
 */
inline fun preComputeTextAndRender(
    textView: TextView,
    crossinline computeText: () -> CharSequence,
    crossinline onBeforeComputeText: () -> Unit = {},
    crossinline onAfterComputeText: (CharSequence) -> Unit = {},
    coroutineScope: CoroutineScope,
    calculateDispatcher: CoroutineDispatcher,
    renderDispatcher: CoroutineDispatcher
): Job {
    val textViewRef = WeakReference(textView)

    return coroutineScope.launch {
        val preComputedText = withContext(calculateDispatcher) {
            textViewRef.get()?.let {
                preComputeText(
                    textView = it,
                    computeText = computeText,
                    onBeforeComputeText = onBeforeComputeText,
                    onAfterComputeText = onAfterComputeText
                )
            }
        }

        withContext(renderDispatcher) {
            if (preComputedText != null && this.isActive) {
                textViewRef.get()?.let {
                    TextViewCompat.setPrecomputedText(it, preComputedText)
                }
            }
        }
    }
}

/**
 * A convenience function for [preComputeTextAndRender] where the [TextView] used is [this]
 * [TextView].
 *
 * @see [preComputeTextAndRender]
 */
@JvmName("preComputeTextAndRenderForTextView")
inline fun TextView.preComputeTextAndRender(
    crossinline computeText: () -> CharSequence,
    crossinline onBeforeComputeText: () -> Unit = {},
    crossinline onAfterComputeText: (CharSequence) -> Unit = {},
    coroutineScope: CoroutineScope,
    calculateDispatcher: CoroutineDispatcher,
    renderDispatcher: CoroutineDispatcher
): Job {
    val textViewRef = WeakReference(this)

    return coroutineScope.launch {
        val preComputedText = withContext(calculateDispatcher) {
            textViewRef.get()?.let {
                preComputeText(
                    textView = it,
                    computeText = computeText,
                    onBeforeComputeText = onBeforeComputeText,
                    onAfterComputeText = onAfterComputeText
                )
            }
        }

        withContext(renderDispatcher) {
            if (preComputedText != null && this.isActive) {
                textViewRef.get()?.let {
                    TextViewCompat.setPrecomputedText(it, preComputedText)
                }
            }
        }
    }
}

/**
 * Creates a [CharSequence] using the provided [textComputer], then alters that [CharSequence] by
 * iterating through the optional [inOrderTextComputers] and providing the previous resulting
 * [CharSequence]. The resulting [CharSequence] is then returned.
 */
inline fun computeText(
    textComputer: () -> CharSequence,
    vararg inOrderTextComputers: (CharSequence) -> CharSequence
): CharSequence {
    var text = textComputer()

    inOrderTextComputers.forEach {
        text = it.invoke(text)
    }

    return text
}
