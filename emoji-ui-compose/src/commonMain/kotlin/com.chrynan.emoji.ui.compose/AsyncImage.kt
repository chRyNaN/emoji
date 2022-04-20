package com.chrynan.emoji.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale

@Composable
internal expect fun InternalAsyncImage(
    uri: String,
    modifier: Modifier,
    contentDescription: String?,
    alignment: Alignment,
    contentScale: ContentScale,
    alpha: Float,
    colorFilter: ColorFilter?,
    filterQuality: FilterQuality,
    isCrossFadeEnabled: Boolean
)

@Composable
internal fun AsyncImage(
    uri: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
    alpha: Float = DefaultAlpha,
    colorFilter: ColorFilter? = null,
    filterQuality: FilterQuality = DrawScope.DefaultFilterQuality,
    isCrossFadeEnabled: Boolean = true
) {
    InternalAsyncImage(
        uri = uri,
        modifier = modifier,
        contentDescription = contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality,
        isCrossFadeEnabled = isCrossFadeEnabled
    )
}
