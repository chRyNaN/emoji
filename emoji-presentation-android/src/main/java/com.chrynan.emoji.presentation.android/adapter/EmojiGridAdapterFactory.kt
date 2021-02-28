package com.chrynan.emoji.presentation.android.adapter

import android.content.Context
import com.chrynan.aaaah.AdapterPositionManager
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.BaseAdapterFactory
import com.chrynan.aaaah.GridPositionManager
import com.chrynan.dispatchers.CoroutineDispatchers
import com.chrynan.emoji.presentation.core.viewmodel.ListItemViewModel
import kotlinx.coroutines.CoroutineDispatcher

class EmojiGridAdapterFactory(
    dispatchers: CoroutineDispatchers = com.chrynan.dispatchers.dispatchers,
    emojiListItemAdapter: EmojiGridListItemAdapter,
    context: Context,
    gridColumnCount: Int = 5
) : BaseAdapterFactory<ListItemViewModel>() {

    override val adapters: Set<AnotherAdapter<*>> = setOf(emojiListItemAdapter)

    override val positionManager: AdapterPositionManager = GridPositionManager(context, gridColumnCount)

    override val processDispatcher: CoroutineDispatcher = dispatchers.io

    override val uiDispatcher: CoroutineDispatcher = dispatchers.main
}
