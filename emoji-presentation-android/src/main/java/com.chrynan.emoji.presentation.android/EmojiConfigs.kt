@file:Suppress("unused")

package com.chrynan.emoji.presentation.android

import android.content.Context
import androidx.core.provider.FontRequest
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig

/**
 * An object providing common [EmojiCompat.Config] instances for Android.
 */
object EmojiConfigs {

    /**
     * Retrieves an [EmojiCompat.Config] instance for the bundled font for this application using
     * the provided [context].
     */
    fun getBundled(context: Context): EmojiCompat.Config = BundledEmojiCompatConfig(context)

    /**
     * Retrieves an [EmojiCompat.Config] instance for the "Noto Color Emoji Compat" font using the
     * provided [context].
     */
    fun getNoto(context: Context): EmojiCompat.Config {
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )

        return FontRequestEmojiCompatConfig(context, fontRequest)
    }

    /**
     * Retrieves an [EmojiCompat.Config] from the provided [fontRequest] and [context].
     */
    fun getFromFont(
        context: Context,
        fontRequest: FontRequest
    ): EmojiCompat.Config = FontRequestEmojiCompatConfig(context, fontRequest)
}
