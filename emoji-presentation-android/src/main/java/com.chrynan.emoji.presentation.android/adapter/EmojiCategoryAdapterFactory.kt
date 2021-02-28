package com.chrynan.emoji.presentation.android.adapter

import android.content.Context
import com.chrynan.aaaah.AdapterPositionManager
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.BaseAdapterFactory
import com.chrynan.aaaah.HorizontalPositionManager
import com.chrynan.dispatchers.CoroutineDispatchers
import com.chrynan.emoji.presentation.core.viewmodel.ListItemViewModel
import kotlinx.coroutines.CoroutineDispatcher

class EmojiCategoryAdapterFactory(
    dispatchers: CoroutineDispatchers,
    emojiCategoryListItemAdapter: EmojiCategoryListItemAdapter,
    context: Context
) : BaseAdapterFactory<ListItemViewModel>() {

    override val adapters: Set<AnotherAdapter<*>> = setOf(emojiCategoryListItemAdapter)

    override val positionManager: AdapterPositionManager = HorizontalPositionManager(context)

    override val processDispatcher: CoroutineDispatcher = dispatchers.io

    override val uiDispatcher: CoroutineDispatcher = dispatchers.main
}
