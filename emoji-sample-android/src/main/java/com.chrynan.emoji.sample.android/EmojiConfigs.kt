package com.chrynan.emoji.sample.android

import android.content.Context
import androidx.core.provider.FontRequest
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.chrynan.sample.R

object EmojiConfigs {

    fun getDefault(context: Context): EmojiCompat.Config = BundledEmojiCompatConfig(context)

    fun getGoogle(context: Context): EmojiCompat.Config {
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )

        return FontRequestEmojiCompatConfig(context, fontRequest)
    }
}
