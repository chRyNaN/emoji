@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.aaaah.bindAdapterFactory
import com.chrynan.aaaah.calculateAndDispatchDiff
import com.chrynan.dispatchers.dispatchers
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.adapter.EmojiCategoryAdapterFactory
import com.chrynan.emoji.presentation.android.adapter.EmojiGridAdapterFactory
import com.chrynan.emoji.presentation.android.util.getParentCallbackOrThrow
import com.chrynan.emoji.presentation.android.util.mapEach
import com.chrynan.emoji.presentation.core.EmojiCategoryListMapper
import com.chrynan.emoji.presentation.core.EmojiMapper
import com.chrynan.emoji.presentation.core.listener.EmojiCategoryListItemSelectedListener
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiCategoryListItemViewModel
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

abstract class BaseEmojiBottomSheetDialogFragment : BaseBottomSheetDialogFragment(),
    EmojiListItemSelectedListener,
    EmojiCategoryListItemSelectedListener {

    abstract val repository: EmojiRepository

    abstract val emojiGridAdapterFactory: EmojiGridAdapterFactory

    abstract val emojiCategoryAdapterFactory: EmojiCategoryAdapterFactory

    abstract val listener: EmojiListItemSelectedListener

    abstract val emojiCategoryMapper: EmojiCategoryListMapper

    open val emojiMapper: EmojiMapper = EmojiMapper()

    open val errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> }

    open val ioDispatcher: CoroutineDispatcher = dispatchers.io

    open val mainDispatcher: CoroutineDispatcher = dispatchers.main

    private var currentCategory: EmojiCategoryListItemViewModel? = null
    private var emojiCategories: List<EmojiCategoryListItemViewModel> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.dialog_emoji_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialogEmojiRecyclerView = view.findViewById<RecyclerView>(R.id.dialogEmojiRecyclerView)
        val dialogEmojiCategoryRecyclerView = view.findViewById<RecyclerView>(R.id.dialogEmojiCategoryRecyclerView)

        dialogEmojiRecyclerView?.bindAdapterFactory(emojiGridAdapterFactory)
        dialogEmojiCategoryRecyclerView?.bindAdapterFactory(emojiCategoryAdapterFactory)

        flow { emit(repository.getAll().toList()) }
            .map { emojiCategoryMapper.map(it) }
            .onEach { currentCategory = it.firstOrNull() }
            .mapEach { it.copy(isSelected = it.category == currentCategory?.category) }
            .onEach { emojiCategories = it }
            .onStart { emit(emojiCategories) }
            .flowOn(ioDispatcher)
            .calculateAndDispatchDiff(emojiCategoryAdapterFactory)
            .mapNotNull { currentCategory?.emojis }
            .calculateAndDispatchDiff(emojiGridAdapterFactory)
            .catch { errorHandler(it, "Error loading emojis in EmojiBottomSheetDialogFragment.") }
            .launchIn(this)
    }

    override fun onEmojiListItemSelected(item: EmojiViewModel) {
        dismiss()

        listener.onEmojiListItemSelected(item)
    }

    override fun onEmojiCategoryListItemSelected(item: EmojiCategoryListItemViewModel) {
        currentCategory = item
        emojiCategories = emojiCategories.map { it.copy(isSelected = it.category == item.category) }

        flow { emit(item.emojis) }
            .calculateAndDispatchDiff(emojiGridAdapterFactory)
            .catch { errorHandler(it, "Error loading emojis in EmojiBottomSheetDialogFragment.") }
            .launchIn(this)

        flow { emit(emojiCategories) }
            .calculateAndDispatchDiff(emojiCategoryAdapterFactory)
            .catch { errorHandler(it, "Error loading emoji categories in EmojiBottomSheetDialogFragment.") }
            .launchIn(this)
    }
}

@Suppress("FunctionName")
fun EmojiBottomSheetDialogFragment(
    repository: EmojiRepository,
    emojiGridAdapterFactory: EmojiGridAdapterFactory,
    emojiCategoryAdapterFactory: EmojiCategoryAdapterFactory,
    listener: EmojiListItemSelectedListener? = null,
    categoryMapper: EmojiCategoryListMapper,
    emojiMapper: EmojiMapper = EmojiMapper(),
    ioDispatcher: CoroutineDispatcher = dispatchers.io,
    mainDispatcher: CoroutineDispatcher = dispatchers.main,
    errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> }
): BaseEmojiBottomSheetDialogFragment =
    object : BaseEmojiBottomSheetDialogFragment() {

        override val repository: EmojiRepository
            get() = repository

        override val emojiGridAdapterFactory: EmojiGridAdapterFactory
            get() = emojiGridAdapterFactory

        override val emojiCategoryAdapterFactory: EmojiCategoryAdapterFactory
            get() = emojiCategoryAdapterFactory

        override val listener: EmojiListItemSelectedListener
            get() = listener ?: getParentCallbackOrThrow()

        override val emojiCategoryMapper: EmojiCategoryListMapper
            get() = categoryMapper

        override val emojiMapper: EmojiMapper
            get() = emojiMapper

        override val errorHandler: (exception: Throwable, message: String) -> Unit
            get() = errorHandler

        override val ioDispatcher: CoroutineDispatcher
            get() = ioDispatcher

        override val mainDispatcher: CoroutineDispatcher
            get() = mainDispatcher
    }

@Suppress("FunctionName")
fun EmojiBottomSheetDialogFragment(
    repository: EmojiRepository,
    emojiGridAdapterFactory: EmojiGridAdapterFactory,
    emojiCategoryAdapterFactory: EmojiCategoryAdapterFactory,
    listener: EmojiListItemSelectedListener? = null,
    uncategorizedTitle: CharSequence,
    emojiMapper: EmojiMapper = EmojiMapper(),
    ioDispatcher: CoroutineDispatcher = dispatchers.io,
    mainDispatcher: CoroutineDispatcher = dispatchers.main,
    errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> }
): BaseEmojiBottomSheetDialogFragment =
    object : BaseEmojiBottomSheetDialogFragment() {

        override val repository: EmojiRepository
            get() = repository

        override val emojiGridAdapterFactory: EmojiGridAdapterFactory
            get() = emojiGridAdapterFactory

        override val emojiCategoryAdapterFactory: EmojiCategoryAdapterFactory
            get() = emojiCategoryAdapterFactory

        override val listener: EmojiListItemSelectedListener
            get() = listener ?: getParentCallbackOrThrow()

        override val emojiCategoryMapper: EmojiCategoryListMapper
            get() = EmojiCategoryListMapper(uncategorizedTitle = uncategorizedTitle)

        override val emojiMapper: EmojiMapper
            get() = emojiMapper

        override val errorHandler: (exception: Throwable, message: String) -> Unit
            get() = errorHandler

        override val ioDispatcher: CoroutineDispatcher
            get() = ioDispatcher

        override val mainDispatcher: CoroutineDispatcher
            get() = mainDispatcher
    }
