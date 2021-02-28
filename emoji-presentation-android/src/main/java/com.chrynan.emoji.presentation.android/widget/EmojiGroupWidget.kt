package com.chrynan.emoji.presentation.android.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class EmojiGroupWidget(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    LinearLayout(context, attrs, defStyleAttr) {

    sealed class Direction {

        class Vertical(val startSide: Side = Side.START_TOP) : Direction()

        class Horizontal(val startSide: Side = Side.START_TOP) : Direction()
    }

    enum class Side {

        START_TOP,
        END_BOTTOM
    }

    enum class SizePolicy {

        FIT,
        NONE
    }

    data class ViewModel(
        val direction: Direction = Direction.Horizontal(),
        val sizePolicy: SizePolicy = SizePolicy.NONE
    )
}
