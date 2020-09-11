@file:Suppress("unused")

package com.chrynan.emoji.buildSrc.util

import com.chrynan.emoji.buildSrc.model.MimicEmoji
import groovy.json.JsonBuilder

fun MimicEmoji.toJson(): String = JsonBuilder(this).toString()

fun List<MimicEmoji>.toJson(): String = JsonBuilder(this).toString()

fun List<MimicEmoji>.toPrettyJson(): String = JsonBuilder(this).toPrettyString()
