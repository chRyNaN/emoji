package com.chrynan.emoji.presentation.android.dialog

import androidx.annotation.StyleRes
import com.chrynan.dispatchers.CoroutineDispatchers
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.util.collatorCompareBy
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiCategoryListItemViewModel

class EmojiBottomSheetDialogFragment(
    override val repository: EmojiRepository,
    override val categoryComparator: Comparator<EmojiCategoryListItemViewModel> = collatorCompareBy { it.category.toString() },
    override val dispatchers: CoroutineDispatchers = com.chrynan.dispatchers.dispatchers,
    override val errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> },
    override val emojiListItemSelectedListener: EmojiListItemSelectedListener? = null,
    override val gridColumnCount: Int = 5,
    override val uncategorizedTitle: CharSequence = "No Category",
    @StyleRes override val customStyle: Int? = R.style.EmojiBottomSheetStyle
) : BaseEmojiBottomSheetDialogFragment()
