package com.chrynan.emoji.presentation.android.util

import com.chrynan.emoji.presentation.core.util.NumberFormatter
import java.text.NumberFormat
import java.util.*

/**
 * An Android implementation of [NumberFormatter].
 */
class AndroidNumberFormatter(
    private val numberFormatterRetriever: () -> NumberFormat = {
        NumberFormat.getInstance(
            Locale.getDefault()
        )
    }
) : NumberFormatter {

    override fun format(value: Number): String {
        val formatter = numberFormatterRetriever.invoke()

        return when (value) {
            is Long -> formatter.format(value)
            is Double -> formatter.format(value)
            else -> formatter.format(value.toLong())
        }
    }
}
