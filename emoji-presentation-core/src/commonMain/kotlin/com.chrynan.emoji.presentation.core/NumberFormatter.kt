package com.chrynan.emoji.presentation.core

/**
 * A component that formats [Number] values into [String] values according to the current locale.
 */
interface NumberFormatter {

    /**
     * Formats the provided [Number] [value] into a [String]. For instance, a value of 1000L would
     * be formatted to "1,000" for a current locale of Locale.US.
     */
    fun format(value: Number): String
}
