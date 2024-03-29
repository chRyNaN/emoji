@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import androidx.annotation.StyleRes
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import com.chrynan.dispatchers.CoroutineDispatchers
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.dialog.DelegateFragmentFactory
import com.chrynan.emoji.presentation.android.dialog.EmojiBottomSheetDialogFragment
import com.chrynan.emoji.presentation.android.dialog.EmojiBottomSheetDialogFragmentFactory
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiCategoryListItemViewModel

/**
 * Creates an instance of an [EmojiBottomSheetDialogFragment] and shows it using the
 * [EmojiBottomSheetDialogFragment.show] function, then returns the
 * [EmojiBottomSheetDialogFragment] for future use.
 */
fun FragmentManager.showEmojiPicker(
    repository: EmojiRepository,
    categoryComparator: Comparator<EmojiCategoryListItemViewModel> = collatorCompareBy { it.category.toString() },
    dispatchers: CoroutineDispatchers = com.chrynan.dispatchers.dispatchers,
    errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> },
    emojiListItemSelectedListener: EmojiListItemSelectedListener? = null,
    gridColumnCount: Int = 5,
    uncategorizedTitle: CharSequence = "No Category",
    @StyleRes customStyle: Int? = R.style.EmojiBottomSheetStyle,
    fragmentTag: String? = null,
    retainFragmentFactory: Boolean = true
): EmojiBottomSheetDialogFragment {
    val factory = EmojiBottomSheetDialogFragmentFactory(
        repository = repository,
        categoryComparator = categoryComparator,
        dispatchers = dispatchers,
        errorHandler = errorHandler,
        emojiListItemSelectedListener = emojiListItemSelectedListener,
        gridColumnCount = gridColumnCount,
        uncategorizedTitle = uncategorizedTitle,
        customStyle = customStyle
    )

    if (retainFragmentFactory) {
        delegateFragmentFactory(factory)
    } else {
        fragmentFactory = factory
    }

    val fragment = EmojiBottomSheetDialogFragment(
        repository = repository,
        categoryComparator = categoryComparator,
        dispatchers = dispatchers,
        errorHandler = errorHandler,
        emojiListItemSelectedListener = emojiListItemSelectedListener,
        gridColumnCount = gridColumnCount,
        uncategorizedTitle = uncategorizedTitle,
        customStyle = customStyle
    )

    fragment.show(this, fragmentTag)

    return fragment
}

/**
 * Creates an instance of an [EmojiBottomSheetDialogFragment] and shows it using the
 * [EmojiBottomSheetDialogFragment.showNow] function, then returns the
 * [EmojiBottomSheetDialogFragment] for future use.
 */
fun FragmentManager.showEmojiPickerNow(
    repository: EmojiRepository,
    categoryComparator: Comparator<EmojiCategoryListItemViewModel> = collatorCompareBy { it.category.toString() },
    dispatchers: CoroutineDispatchers = com.chrynan.dispatchers.dispatchers,
    errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> },
    emojiListItemSelectedListener: EmojiListItemSelectedListener? = null,
    gridColumnCount: Int = 5,
    uncategorizedTitle: CharSequence = "No Category",
    @StyleRes customStyle: Int? = R.style.EmojiBottomSheetStyle,
    fragmentTag: String? = null,
    retainFragmentFactory: Boolean = true
): EmojiBottomSheetDialogFragment {
    val factory = EmojiBottomSheetDialogFragmentFactory(
        repository = repository,
        categoryComparator = categoryComparator,
        dispatchers = dispatchers,
        errorHandler = errorHandler,
        emojiListItemSelectedListener = emojiListItemSelectedListener,
        gridColumnCount = gridColumnCount,
        uncategorizedTitle = uncategorizedTitle,
        customStyle = customStyle
    )

    if (retainFragmentFactory) {
        delegateFragmentFactory(factory)
    } else {
        fragmentFactory = factory
    }

    val fragment = EmojiBottomSheetDialogFragment(
        repository = repository,
        categoryComparator = categoryComparator,
        dispatchers = dispatchers,
        errorHandler = errorHandler,
        emojiListItemSelectedListener = emojiListItemSelectedListener,
        gridColumnCount = gridColumnCount,
        uncategorizedTitle = uncategorizedTitle,
        customStyle = customStyle
    )

    fragment.showNow(this, fragmentTag)

    return fragment
}

internal fun FragmentManager.delegateFragmentFactory(factory: FragmentFactory) {
    val currentFactory = fragmentFactory

    if (currentFactory is DelegateFragmentFactory) {
        currentFactory.factories = currentFactory.factories + factory
        fragmentFactory = currentFactory
    } else {
        fragmentFactory = DelegateFragmentFactory(initialFactories = setOf(currentFactory, factory))
    }
}
