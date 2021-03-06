@file:Suppress("unused")

package com.chrynan.emoji.presentation.android.util

import android.view.View
import android.view.ViewGroup

internal val ViewGroup.childViews: List<View>
    get() = (0 until childCount).map { getChildAt(it) }

internal fun View.setAccessibilityName(name: CharSequence?, includeAsTooltipText: Boolean = true) {
    this.contentDescription = name

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        if (name == null || includeAsTooltipText) {
            this.tooltipText = name
        }
    }
}
