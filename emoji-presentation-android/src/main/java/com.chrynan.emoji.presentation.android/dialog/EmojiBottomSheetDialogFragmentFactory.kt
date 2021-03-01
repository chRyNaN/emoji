package com.chrynan.emoji.presentation.android.dialog

import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import com.chrynan.dispatchers.CoroutineDispatchers
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.util.collatorCompareBy
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiCategoryListItemViewModel

class EmojiBottomSheetDialogFragmentFactory(
    private val repository: EmojiRepository,
    private val categoryComparator: Comparator<EmojiCategoryListItemViewModel> = collatorCompareBy { it.category.toString() },
    private val dispatchers: CoroutineDispatchers = com.chrynan.dispatchers.dispatchers,
    private val errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> },
    private val emojiListItemSelectedListener: EmojiListItemSelectedListener? = null,
    private val gridColumnCount: Int = 5,
    private val uncategorizedTitle: CharSequence = "No Category",
    @StyleRes private val customStyle: Int? = R.style.EmojiBottomSheetStyle
) : DelegatableFragmentFactory() {

    override fun handlesFragment(
        classLoader: ClassLoader,
        className: String,
        klass: Class<*>
    ): Boolean =
        className == EmojiBottomSheetDialogFragment::class.java.name || klass == EmojiBottomSheetDialogFragment::class.java

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        if (className == EmojiBottomSheetDialogFragment::class.java.name ||
            loadFragmentClass(classLoader, className) == EmojiBottomSheetDialogFragment::class.java
        ) {
            EmojiBottomSheetDialogFragment(
                repository = repository,
                categoryComparator = categoryComparator,
                dispatchers = dispatchers,
                errorHandler = errorHandler,
                emojiListItemSelectedListener = emojiListItemSelectedListener,
                gridColumnCount = gridColumnCount,
                uncategorizedTitle = uncategorizedTitle,
                customStyle = customStyle
            )
        } else {
            super.instantiate(classLoader, className)
        }
}
