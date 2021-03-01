@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.aaaah.bindAdapterFactory
import com.chrynan.aaaah.calculateAndDispatchDiff
import com.chrynan.dispatchers.CoroutineDispatchers
import com.chrynan.emoji.core.EmojiRepository
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.adapter.EmojiCategoryAdapterFactory
import com.chrynan.emoji.presentation.android.adapter.EmojiCategoryListItemAdapter
import com.chrynan.emoji.presentation.android.adapter.EmojiGridAdapterFactory
import com.chrynan.emoji.presentation.android.adapter.EmojiGridListItemAdapter
import com.chrynan.emoji.presentation.android.util.collatorCompareBy
import com.chrynan.emoji.presentation.android.util.getParentCallbackOrThrow
import com.chrynan.emoji.presentation.android.util.mapEach
import com.chrynan.emoji.presentation.core.EmojiCategoryListMapper
import com.chrynan.emoji.presentation.core.EmojiMapper
import com.chrynan.emoji.presentation.core.listener.EmojiCategoryListItemSelectedListener
import com.chrynan.emoji.presentation.core.listener.EmojiListItemSelectedListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiCategoryListItemViewModel
import com.chrynan.emoji.presentation.core.viewmodel.EmojiViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.*

abstract class BaseEmojiBottomSheetDialogFragment : BaseBottomSheetDialogFragment(),
    EmojiListItemSelectedListener,
    EmojiCategoryListItemSelectedListener {

    protected abstract val repository: EmojiRepository

    protected open val categoryComparator: Comparator<EmojiCategoryListItemViewModel> =
        collatorCompareBy { it.category.toString() }

    protected open val dispatchers: CoroutineDispatchers = com.chrynan.dispatchers.dispatchers

    protected open val errorHandler: (exception: Throwable, message: String) -> Unit = { _, _ -> }

    protected open val emojiListItemSelectedListener: EmojiListItemSelectedListener? = null

    protected open val gridColumnCount: Int = 5

    protected open val uncategorizedTitle: CharSequence = "No Category"

    @StyleRes
    protected open val customStyle: Int? = R.style.EmojiBottomSheetStyle

    private val emojiGridAdapterFactory: EmojiGridAdapterFactory by lazy {
        EmojiGridAdapterFactory(
            context = requireContext(),
            gridColumnCount = gridColumnCount,
            dispatchers = dispatchers,
            emojiListItemAdapter = EmojiGridListItemAdapter(listener = this)
        )
    }

    private val emojiCategoryAdapterFactory: EmojiCategoryAdapterFactory by lazy {
        EmojiCategoryAdapterFactory(
            context = requireContext(),
            dispatchers = dispatchers,
            emojiCategoryListItemAdapter = EmojiCategoryListItemAdapter(listener = this)
        )
    }

    private val emojiCategoryMapper: EmojiCategoryListMapper by lazy {
        EmojiCategoryListMapper(uncategorizedTitle = uncategorizedTitle)
    }

    private val emojiMapper: EmojiMapper = EmojiMapper()

    private lateinit var listener: EmojiListItemSelectedListener

    private var currentCategory: EmojiCategoryListItemViewModel? = null
    private var emojiCategories: List<EmojiCategoryListItemViewModel> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        customStyle?.let {
            setStyle(BottomSheetDialogFragment.STYLE_NORMAL, it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_emoji_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = emojiListItemSelectedListener ?: getParentCallbackOrThrow()

        val dialogEmojiRecyclerView = view.findViewById<RecyclerView>(R.id.dialogEmojiRecyclerView)
        val dialogEmojiCategoryRecyclerView =
            view.findViewById<RecyclerView>(R.id.dialogEmojiCategoryRecyclerView)

        dialogEmojiRecyclerView?.bindAdapterFactory(emojiGridAdapterFactory)
        dialogEmojiCategoryRecyclerView?.bindAdapterFactory(emojiCategoryAdapterFactory)

        flow { emit(repository.getAll().toList()) }
            .map { emojiCategoryMapper.map(it) }
            .map { it.sortedWith(categoryComparator) }
            .onEach { currentCategory = it.firstOrNull() }
            .mapEach { it.copy(isSelected = it.category == currentCategory?.category) }
            .onEach { emojiCategories = it }
            .onStart { emit(emojiCategories) }
            .flowOn(dispatchers.io)
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
            .catch {
                errorHandler(
                    it,
                    "Error loading emoji categories in EmojiBottomSheetDialogFragment."
                )
            }
            .launchIn(this)
    }
}
