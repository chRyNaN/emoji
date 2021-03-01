package com.chrynan.emoji.sample.android

import android.app.Application
import androidx.emoji.text.EmojiCompat

class SampleApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        EmojiCompat.init(EmojiConfigs.getDefault(this))
    }
}
