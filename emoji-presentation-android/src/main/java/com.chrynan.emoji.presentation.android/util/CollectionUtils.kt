package com.chrynan.emoji.presentation.android.util

// Not the most performant
internal fun <T> Collection<T>?.replace(
    other: Collection<T>,
    addRemainder: Boolean = true,
    matches: (item: T, otherItem: T) -> Boolean,
    replaces: (item: T, otherItem: T) -> T
): List<T> {
    if (this == null) return other.toList()

    val resultList = mutableListOf<T>()
    val otherList = other.toMutableList()

    this.forEach {
        val matchingItem = otherList.firstOrNull { otherItem -> matches.invoke(it, otherItem) }

        if (matchingItem == null) {
            resultList.add(it)
        } else {
            val resultItem = replaces.invoke(it, matchingItem)
            resultList.add(resultItem)
            otherList.remove(matchingItem)
        }
    }

    if (otherList.isNotEmpty() && addRemainder) {
        resultList.addAll(otherList)
    }

    return resultList
}
