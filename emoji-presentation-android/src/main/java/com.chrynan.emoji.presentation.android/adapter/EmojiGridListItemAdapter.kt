package com.chrynan.emoji.presentation.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chrynan.aaaah.*
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.widget.EmojiWidget
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel

@Adapter
class EmojiGridListItemAdapter(private val listener: EmojiListItemSelectedListener) :
    AnotherAdapter<EmojiViewModel>() {

    override val viewType: ViewType = AdapterViewType.from(EmojiGridListItemAdapter::class.java)

    override fun onHandlesItem(item: Any): Boolean = item is EmojiViewModel

    override fun onCreateView(parent: ViewGroup, inflater: LayoutInflater, viewType: ViewType): View =
        inflater.inflate(R.layout.adapter_emoji_list_item, parent, false)

    override fun View.onBindItem(item: EmojiViewModel, position: Int) {
        findViewById<EmojiWidget>(R.id.adapterEmojiWidget).apply {
            emojiViewModel = item

            setOnClickListener { listener.onEmojiListItemSelected(item) }
        }
    }
}
