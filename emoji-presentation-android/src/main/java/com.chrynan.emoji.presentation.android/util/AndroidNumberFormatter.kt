package com.chrynan.emoji.presentation.android.util

import com.chrynan.emoji.presentation.core.util.NumberFormatter
import java.text.NumberFormat
import java.util.*

/**
 * An Android implementation of [NumberFormatter].
 */
class AndroidNumberFormatter : NumberFormatter {

    override fun format(value: Number): String {
        val formatter = NumberFormat.getInstance(Locale.getDefault())

        return when (value) {
            is Long -> formatter.format(value)
            is Double -> formatter.format(value)
            else -> formatter.format(value.toLong())
        }
    }
}
