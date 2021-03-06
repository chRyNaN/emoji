package com.chrynan.emoji.presentation.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chrynan.aaaah.*
import com.chrynan.emoji.core.accessibilityName
import com.chrynan.emoji.presentation.android.util.setAccessibilityName
import com.chrynan.emoji.presentation.android.util.toCharSequence
import com.chrynan.emoji.presentation.core.listener.EmojiCategoryListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiCategoryListItemViewModel

@Adapter
class EmojiCategoryListItemAdapter(private val listener: EmojiCategoryListItemSelectedListener) :
    AnotherAdapter<EmojiCategoryListItemViewModel>() {

    override val viewType: ViewType = AdapterViewType.from(EmojiCategoryListItemAdapter::class.java)

    override fun onHandlesItem(item: Any): Boolean = item is EmojiCategoryListItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View =
        inflater.inflate(
            com.chrynan.emoji.presentation.android.R.layout.adapter_emoji_category_list_item,
            parent,
            false
        )

    override fun View.onBindItem(item: EmojiCategoryListItemViewModel, position: Int) {
        val adapterEmojiCategoryTitleTextView =
            findViewById<TextView>(com.chrynan.emoji.presentation.android.R.id.adapterEmojiCategoryTitleTextView)

        adapterEmojiCategoryTitleTextView?.text = if (item.showEmojiAsTitle) {
            item.categoryEmoji?.toCharSequence(
                context = context,
                onImageLoadError = { _, _ -> adapterEmojiCategoryTitleTextView?.invalidate() },
                onImageLoadSuccess = { _, _ -> adapterEmojiCategoryTitleTextView?.invalidate() })
                ?: item.category
        } else {
            item.category
        }

        adapterEmojiCategoryTitleTextView?.setAccessibilityName(name = if (item.showEmojiAsTitle) item.categoryEmoji?.emoji?.accessibilityName else item.category)

        adapterEmojiCategoryTitleTextView?.isActivated = item.isSelected
        adapterEmojiCategoryTitleTextView?.setOnClickListener {
            adapterEmojiCategoryTitleTextView.isActivated = !item.isSelected
            listener.onEmojiCategoryListItemSelected(item)
        }
    }
}
