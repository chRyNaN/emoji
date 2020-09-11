package com.chrynan.emoji.buildSrc.util

import com.chrynan.emoji.buildSrc.model.MimicEmoji

private const val SMALLER_EMOJI_SUB_LIST_AMOUNT = 6

/**
 * Divides this [List] of [MimicEmoji]s into a [List] of [List]s of [MimicEmoji]s. This divides the [List] into smaller
 * [List]s that are more manageable to work with.
 */
fun List<MimicEmoji>.partitionToSmallerList(): List<List<MimicEmoji>> {
    val maxPerList = size / SMALLER_EMOJI_SUB_LIST_AMOUNT

    val listOfLists = mutableListOf<List<MimicEmoji>>()

    for (i in 0 until SMALLER_EMOJI_SUB_LIST_AMOUNT) {
        val start = i * maxPerList
        val isLastIndex = i == SMALLER_EMOJI_SUB_LIST_AMOUNT - 1
        val end = when {
            isLastIndex -> size
            i * maxPerList + maxPerList < size -> i * maxPerList + maxPerList
            else -> size
        }

        listOfLists.add(subList(fromIndex = start, toIndex = end))
    }

    return listOfLists
}
