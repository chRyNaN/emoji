package com.chrynan.emoji.presentation.core.viewmodel

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.UniqueAdapterItem

actual interface ListItemViewModel : UniqueAdapterItem {

    actual val uniqueId: Long

    override val uniqueAdapterId: AdapterId
        get() = uniqueId
}
