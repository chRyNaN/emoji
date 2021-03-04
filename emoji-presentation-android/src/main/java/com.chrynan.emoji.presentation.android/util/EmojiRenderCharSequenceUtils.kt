@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import android.content.Context
import android.text.SpannableStringBuilder
import coil.Coil
import coil.request.ImageRequest
import coil.request.ImageResult
import com.chrynan.emoji.core.Emoji
import com.chrynan.emoji.presentation.android.span.ImageTargetSpan
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import java.util.regex.Pattern

inline fun EmojiViewModel.toCharSequence(
    context: Context,
    onBuildStart: (SpannableStringBuilder) -> Unit = {},
    onBuildEnd: (SpannableStringBuilder) -> Unit = {},
    crossinline onImageLoadError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onImageLoadSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence = renderEmojiToCharSequence(
    context = context,
    emojiViewModel = this,
    onBuildStart = onBuildStart,
    onBuildEnd = onBuildEnd,
    onImageLoadSuccess = onImageLoadSuccess,
    onImageLoadError = onImageLoadError
)

inline fun renderEmojiToCharSequence(
    context: Context,
    emojiViewModel: EmojiViewModel,
    onBuildStart: (SpannableStringBuilder) -> Unit = {},
    onBuildEnd: (SpannableStringBuilder) -> Unit = {},
    crossinline onImageLoadError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onImageLoadSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence {
    val builder = SpannableStringBuilder()

    onBuildStart.invoke(builder)

    when (val emoji = emojiViewModel.emoji) {
        is Emoji.Unicode -> {
            if (emojiViewModel.isIconPreferred && emoji.iconUri != null) {
                val span = ImageTargetSpan()

                val l = builder.length
                builder.append(" ")
                builder.setSpan(span, l, l + 1, 0)

                val request = ImageRequest.Builder(context)
                    .data(emoji.iconUri)
                    .crossfade(false)
                    .target(span)
                    .listener(
                        onSuccess = { request, metadata -> onImageLoadSuccess(request, metadata) },
                        onError = { request, throwable -> onImageLoadError(request, throwable) })
                    .build()

                Coil.imageLoader(context).enqueue(request)
            } else {
                builder.append(emoji.char)
            }
        }
        is Emoji.Custom -> {
            val uri =
                if (emojiViewModel.isStaticUriPreferred && emoji.staticUri != null) emoji.staticUri else emoji.uri

            val span = ImageTargetSpan()

            val l = builder.length
            builder.append(" ")
            builder.setSpan(span, l, l + 1, 0)

            val request = ImageRequest.Builder(context)
                .data(uri)
                .crossfade(false)
                .target(span)
                .listener(
                    onSuccess = { request, metadata -> onImageLoadSuccess(request, metadata) },
                    onError = { request, throwable -> onImageLoadError(request, throwable) })
                .build()

            Coil.imageLoader(context).enqueue(request)
        }
    }

    onBuildEnd.invoke(builder)

    return builder
}

inline fun renderEmojiToCharSequenceSource(
    source: CharSequence,
    context: Context,
    shortCodes: List<String>,
    emojiViewModel: EmojiViewModel,
    onBuildStart: (SpannableStringBuilder) -> Unit = {},
    onBuildEnd: (SpannableStringBuilder) -> Unit = {},
    crossinline onImageLoadError: (request: ImageRequest, throwable: Throwable) -> Unit = { _, _ -> },
    crossinline onImageLoadSuccess: (request: ImageRequest, metadata: ImageResult.Metadata) -> Unit = { _, _ -> }
): CharSequence {
    val builder = SpannableStringBuilder.valueOf(source)

    onBuildStart(builder)

    shortCodes.forEach { shortCode ->
        val matcher = Pattern.compile(shortCode, Pattern.LITERAL)
            .matcher(source)

        while (matcher.find()) {
            builder.replace(
                matcher.start(), matcher.end(), emojiViewModel.toCharSequence(
                    context = context,
                    onImageLoadSuccess = onImageLoadSuccess,
                    onImageLoadError = onImageLoadError
                )
            )
        }
    }

    onBuildEnd(builder)

    return builder
}
