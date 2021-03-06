@file:Suppress("unused")

package com.chrynan.emoji.presentation.android

import android.content.Context
import androidx.core.provider.FontRequest
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import de.c1710.filemojicompat.FileEmojiCompatConfig
import java.io.File

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

    /**
     * Retrieves an [EmojiCompat.Config] from the provided [fontFile] and [context].
     *
     * Note that if the [fontFile] is located in the device's storage, permissions are required to
     * access it. If the permissions weren't added appropriately, this will result in an error,
     * either in this function or when trying to use emojis.
     */
    fun getFromFile(
        context: Context,
        fontFile: File
    ): EmojiCompat.Config = FileEmojiCompatConfig(context, fontFile)
}

/**
 * A convenience function for calling [EmojiConfigs.getFromFile] by creating a [File] with the
 * provided [fontFilePath].
 *
 * @see [EmojiConfigs.getFromFile]
 */
fun EmojiConfigs.getFromFile(
    context: Context,
    fontFilePath: String
): EmojiCompat.Config {
    val fontFile = File(fontFilePath)

    return FileEmojiCompatConfig(context, fontFile)
}
