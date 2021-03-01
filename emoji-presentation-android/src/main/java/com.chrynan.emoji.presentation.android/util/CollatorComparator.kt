package com.chrynan.emoji.presentation.android.util

import java.text.Collator

internal class CollatorComparator<T>(
    private val collator: Collator = Collator.getInstance().apply { strength = Collator.IDENTICAL },
    private val selector: (T) -> String
) : Comparator<T> {

    override fun compare(o1: T, o2: T): Int {
        val s1 = selector(o1)
        val s2 = selector(o2)

        return collator.compare(s1, s2)
    }
}

internal fun <T> collatorCompareBy(selector: (T) -> String): Comparator<T> =
    CollatorComparator(selector = selector)
