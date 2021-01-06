@file:Suppress("unused")

package com.chrynan.emoji.presentation.android

import android.content.Context
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import coil.Coil
import coil.request.ImageRequest
import coil.request.ImageResult
import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.presentation.core.EmojiViewModel
import java.util.regex.Pattern

fun CharSequence.emojify(emojiViewModel: EmojiViewModel, viewToUpdate: View): CharSequence =
    emojify(shortcode = emojiViewModel.emoji.name, emojiViewModel = emojiViewModel, viewToUpdate = viewToUpdate)

fun CharSequence.emojify(shortcode: String, emojiViewModel: EmojiViewModel, viewToUpdate: View): CharSequence =
    emojify(
        shortcode = shortcode,
        emojiViewModel = emojiViewModel,
        context = viewToUpdate.context,
        onSuccess = { _, _ -> viewToUpdate.invalidate() },
        onError = { _, _ -> viewToUpdate.invalidate() }
    )

inline fun CharSequence.emojify(
    emojiViewModel: EmojiViewModel,
    context: Context,
    crossinline onError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence =
    emojify(
        shortcode = emojiViewModel.emoji.name,
        emojiViewModel = emojiViewModel,
        context = context,
        onSuccess = onSuccess,
        onError = onError
    )

inline fun CharSequence.emojify(
    shortcode: String,
    emojiViewModel: EmojiViewModel,
    context: Context,
    crossinline onError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence =
    emojifyAll(
        shortcodeEmojiMap = mapOf(shortcode to emojiViewModel),
        context = context,
        onSuccess = onSuccess,
        onError = onError
    )

inline fun CharSequence.emojify(
    emojis: List<EmojiViewModel>,
    context: Context,
    crossinline onError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence =
    emojifyAll(
        shortcodeEmojiMap = emojis.associateBy { it.name },
        context = context,
        onSuccess = onSuccess,
        onError = onError
    )

inline fun CharSequence.emojify(
    shortcode: String,
    uri: String?,
    context: Context,
    crossinline onError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence = emojify(
    shortcodeUriMap = mapOf(shortcode to uri),
    context = context,
    onError = onError,
    onSuccess = onSuccess
)

inline fun CharSequence.emojify(
    shortcodeUriMap: Map<String, String?>,
    context: Context,
    crossinline onError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence {
    val builder = SpannableStringBuilder.valueOf(this)

    shortcodeUriMap.entries.forEach { (shortcode, uri) ->
        val matcher = Pattern.compile(shortcode, Pattern.LITERAL)
            .matcher(this)

        while (matcher.find()) {
            val span = ImageTargetSpan()

            builder.setSpan(span, matcher.start(), matcher.end(), 0)

            val request = ImageRequest.Builder(context)
                .data(uri)
                .crossfade(false)
                .target(span)
                .listener(
                    onSuccess = { request, metadata -> onSuccess(request, metadata) },
                    onError = { request, throwable -> onError(request, throwable) })
                .build()

            Coil.imageLoader(context).enqueue(request)
        }
    }

    return builder
}

fun CharSequence.emojify(
    shortcode: String,
    char: String
): CharSequence = emojify(shortcodeCharMap = mapOf(shortcode to char))

fun CharSequence.emojify(
    shortcodeCharMap: Map<String, String>
): CharSequence {
    val builder = SpannableStringBuilder.valueOf(this)

    shortcodeCharMap.entries.forEach { (shortcode, char) ->
        val matcher = Pattern.compile(shortcode, Pattern.LITERAL)
            .matcher(this)

        while (matcher.find()) {
            builder.replace(matcher.start(), matcher.end(), char)
        }
    }

    return builder
}

inline fun CharSequence.emojifyAll(
    shortcodeEmojiMap: Map<String, EmojiViewModel?>,
    context: Context,
    crossinline onError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence {
    val builder = SpannableStringBuilder.valueOf(this)

    shortcodeEmojiMap.entries.forEach { (shortcode, emojiViewModel) ->
        if (emojiViewModel != null) {
            val matcher = Pattern.compile(shortcode, Pattern.LITERAL)
                .matcher(this)

            while (matcher.find()) {
                when (val emoji = emojiViewModel.emoji) {
                    is Emoji.Unicode -> {
                        if (emojiViewModel.isIconPreferred && emoji.iconUri != null) {
                            val span = ImageTargetSpan()

                            builder.setSpan(span, matcher.start(), matcher.end(), 0)

                            val request = ImageRequest.Builder(context)
                                .data(emoji.iconUri)
                                .crossfade(false)
                                .target(span)
                                .listener(
                                    onSuccess = { request, metadata -> onSuccess(request, metadata) },
                                    onError = { request, throwable -> onError(request, throwable) })
                                .build()

                            Coil.imageLoader(context).enqueue(request)
                        } else {
                            builder.replace(matcher.start(), matcher.end(), emoji.char)
                        }
                    }
                    is Emoji.Custom -> {
                        val uri =
                            if (emojiViewModel.isStaticUriPreferred && emoji.staticUri != null) emoji.staticUri else emoji.uri

                        val span = ImageTargetSpan()

                        builder.setSpan(span, matcher.start(), matcher.end(), 0)

                        val request = ImageRequest.Builder(context)
                            .data(uri)
                            .crossfade(false)
                            .target(span)
                            .listener(
                                onSuccess = { request, metadata -> onSuccess(request, metadata) },
                                onError = { request, throwable -> onError(request, throwable) })
                            .build()

                        Coil.imageLoader(context).enqueue(request)
                    }
                }
            }
        }
    }

    return builder
}

fun TextView.emojify(emojiViewModel: EmojiViewModel) {
    text = text.emojify(emojiViewModel = emojiViewModel, viewToUpdate = this)
}

fun TextView.emojify(shortcode: String, emojiViewModel: EmojiViewModel) {
    text = text.emojify(shortcode = shortcode, emojiViewModel = emojiViewModel, viewToUpdate = this)
}

fun TextView.emojify(emojis: List<EmojiViewModel>) {
    text = text.emojify(
        emojis = emojis,
        context = context,
        onError = { _, _ -> invalidate() },
        onSuccess = { _, _ -> invalidate() })
}

fun TextView.emojify(emojis: Map<String, EmojiViewModel>) {
    text = text.emojifyAll(
        shortcodeEmojiMap = emojis,
        context = context,
        onError = { _, _ -> invalidate() },
        onSuccess = { _, _ -> invalidate() })
}
