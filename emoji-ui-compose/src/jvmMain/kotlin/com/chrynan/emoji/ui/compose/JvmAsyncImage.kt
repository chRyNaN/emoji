package com.chrynan.emoji.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import com.chrynan.ui.components.image.AsyncImage

@Composable
internal actual fun InternalAsyncImage(
    uri: String,
    modifier: Modifier,
    contentDescription: String?,
    alignment: Alignment,
    contentScale: ContentScale,
    alpha: Float,
    colorFilter: ColorFilter?,
    filterQuality: FilterQuality,
    isCrossFadeEnabled: Boolean
) {
    AsyncImage(
        modifier = modifier,
        data = uri,
        contentDescription = contentDescription,
        alignment = alignment,
        contentScale = contentScale,
        alpha = alpha,
        colorFilter = colorFilter,
        filterQuality = filterQuality,
        isCrossFadeEnabled = isCrossFadeEnabled
    )
}
