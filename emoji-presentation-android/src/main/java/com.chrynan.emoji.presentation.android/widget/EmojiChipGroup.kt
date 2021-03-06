@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.chrynan.emoji.presentation.android.widget

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.chrynan.emoji.presentation.android.R
import com.chrynan.emoji.presentation.android.util.childViews
import com.chrynan.emoji.presentation.android.util.replace
import com.chrynan.emoji.presentation.core.listener.EmojiChipGroupListener
import com.chrynan.emoji.presentation.core.viewmodel.EmojiChipGroupViewModel
import com.chrynan.emoji.presentation.core.viewmodel.EmojiChipViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * A custom [ChipGroup] that displays [EmojiChip]s with a [Chip] to add more emojis.
 */
class EmojiChipGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.EmojiChipGroup
) : ChipGroup(context, attrs, defStyleAttr) {

    val emojiChips: List<EmojiChip>
        get() = childViews.filterIsInstance<EmojiChip>()

    var viewModel: EmojiChipGroupViewModel? = null
        set(value) {
            field = value

            updateEmojiChipViews(value?.chips ?: emptySet())
        }

    var listener: EmojiChipGroupListener? = null

    var emojiChipCreator: (context: Context, viewModel: EmojiChipViewModel) -> EmojiChip =
        defaultEmojiChipCreator

    var addEmojiChipCreator: (context: Context) -> Chip = defaultAddEmojiChipCreator

    var emojiCountCalculator: (item: EmojiChipViewModel, otherItem: EmojiChipViewModel) -> Long? =
        defaultEmojiCountCalculator

    init {
        // This is needed so we always show the "add more" chip
        viewModel = null
    }

    fun addEmojiChip(chip: EmojiChipViewModel) = addEmojiChips(chips = setOf(chip))

    fun addEmojiChips(chips: Set<EmojiChipViewModel>) {
        val newChips = viewModel?.chips.replace(
            other = chips,
            matches = { item, otherItem -> item.emojiViewModel == otherItem.emojiViewModel },
            replaces = { item, otherItem ->
                val updatedCount = emojiCountCalculator.invoke(item, otherItem)
                otherItem.copy(count = updatedCount)
            })
            .toSet()

        viewModel = viewModel?.copy(chips = newChips) ?: EmojiChipGroupViewModel(chips = newChips)
    }

    fun removeEmojiChip(chip: EmojiChipViewModel) = removeEmojiChips(chips = setOf(chip))

    fun removeEmojiChips(chips: Set<EmojiChipViewModel>) {
        viewModel = viewModel?.copy(chips = (viewModel?.chips ?: emptySet()) - chips)
    }

    private fun updateEmojiChipViews(chipViewModels: Set<EmojiChipViewModel>) {
        removeAllViews()

        chipViewModels.map { emojiChipCreator.invoke(context, it) }
            .forEach { chip ->
                addView(chip)
                chip.setOnClickListener {
                    chip.viewModel?.let { handleEmojiChipClick(it) }
                }
            }

        val addEmojiChip = addEmojiChipCreator.invoke(context).apply {
            setOnClickListener { listener?.onAddEmojiSelected() }
        }

        addView(addEmojiChip)
    }

    private fun handleEmojiChipClick(viewModel: EmojiChipViewModel) {
        val isSelecting = !viewModel.isSelected
        val oldCount = viewModel.count
        val newCount = when {
            oldCount == null -> null
            !isSelecting -> oldCount - 1
            isSelecting -> oldCount + 1
            else -> null
        }
        val updatedViewModel =
            viewModel.copy(isSelected = isSelecting, count = newCount)

        if ((newCount ?: 0) <= 0 && !isSelecting) {
            // The View Model was selected before the click, which means unselected now,
            // and there was only one selection before, so we remove it and update the model
            removeEmojiChip(viewModel)
        } else {
            // We update the current chip with the new chip values. This should work since
            // the EmojiChipViewModel overrides the equals and only considers the Emoji for
            // whether View Models are equal.
            addEmojiChip(updatedViewModel)
        }

        listener?.onChipSelected(updatedViewModel)
    }
}

/**
 * A convenience function to create an [EmojiChipGroup] with the provided [viewModel]
 * and [listener].
 */
@Suppress("FunctionName")
fun EmojiChipGroup(
    viewModel: EmojiChipGroupViewModel,
    listener: EmojiChipGroupListener,
    emojiChipCreator: (context: Context, viewModel: EmojiChipViewModel) -> EmojiChip = defaultEmojiChipCreator,
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.style.EmojiChipGroup,
    addEmojiChipCreator: (context: Context) -> Chip = defaultAddEmojiChipCreator,
    emojiCountCalculator: (item: EmojiChipViewModel, otherItem: EmojiChipViewModel) -> Long? = defaultEmojiCountCalculator
): EmojiChipGroup {
    val group = EmojiChipGroup(context, attrs, defStyleAttr)
    group.listener = listener
    group.viewModel = viewModel
    group.emojiChipCreator = emojiChipCreator
    group.addEmojiChipCreator = addEmojiChipCreator
    group.emojiCountCalculator = emojiCountCalculator
    return group
}

private val defaultEmojiChipCreator: (context: Context, viewModel: EmojiChipViewModel) -> EmojiChip =
    { context, viewModel ->
        EmojiChip(
            context = context,
            viewModel = viewModel,
            defStyleAttr = R.style.EmojiChipChoiceStyle
        )
    }

private val defaultAddEmojiDrawableFetcher: (context: Context, resources: Resources) -> Drawable? =
    { _, resources ->
        ResourcesCompat.getDrawable(
            resources,
            R.drawable.ic_default_emoji_24,
            null
        )
    }

private val defaultAddEmojiChipCreator: (context: Context) -> Chip =
    { context ->
        Chip(context, null, R.style.Widget_MaterialComponents_Chip_Action).apply {
            chipIcon = defaultAddEmojiDrawableFetcher.invoke(context, resources)
            text = "+"
            setTextAppearance(R.style.TextAppearance_MaterialComponents_Subtitle1)
        }
    }

private val defaultEmojiCountCalculator: (item: EmojiChipViewModel, otherItem: EmojiChipViewModel) -> Long? =
    { item, otherItem ->
        val itemCount = item.count
        val otherItemCount = otherItem.count

        when {
            itemCount == null && otherItemCount == null -> null
            itemCount == null && otherItemCount != null -> otherItemCount
            itemCount != null && otherItemCount == null -> null
            itemCount != null && itemCount == otherItemCount && item.isSelected && !otherItem.isSelected -> itemCount - 1
            itemCount != null && itemCount == otherItemCount && !item.isSelected && otherItem.isSelected -> itemCount + 1
            otherItemCount != null -> otherItemCount
            else -> null
        }
    }
