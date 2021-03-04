package com.chrynan.emoji.sample.android

import android.app.Application
import com.chrynan.emoji.core.Emojis
import com.chrynan.emoji.presentation.android.EmojiConfigs
import com.chrynan.emoji.presentation.android.util.init

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Emojis.init(EmojiConfigs.getBundled(this))
    }
}
