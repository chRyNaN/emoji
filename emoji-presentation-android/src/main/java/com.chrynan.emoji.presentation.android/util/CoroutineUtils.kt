package com.chrynan.emoji.presentation.android.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun <T, R> Flow<Collection<T>>.mapEach(mapper: suspend (T) -> R): Flow<List<R>> =
    map { list -> list.map { mapper.invoke(it) } }
