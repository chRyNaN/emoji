package com.chrynan.emoji.presentation.android.dialog

import com.chrynan.dispatchers.CoroutineDispatchers
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener

class EmojiBottomSheetDialogFragment(
    override val repository: EmojiRepository,
    override val dispatchers: CoroutineDispatchers = com.chrynan.dispatchers.dispatchers,
    override val errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> },
    override val emojiListItemSelectedListener: EmojiListItemSelectedListener? = null,
    override val gridColumnCount: Int = 5,
    override val uncategorizedTitle: CharSequence = "No Category"
) : BaseEmojiBottomSheetDialogFragment()
