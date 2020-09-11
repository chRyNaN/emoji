@file:Suppress("unused")

package com.chrynan.emoji.buildSrc.util

import com.chrynan.emoji.buildSrc.model.MimicEmoji
import groovy.json.JsonSlurper
import java.io.File

@Suppress("UNCHECKED_CAST")
fun getMimicEmojisFromFilePath(filePath: String): List<MimicEmoji> {
    val file = File(filePath)

    if (file.exists()) {
        val jsonList = (JsonSlurper().parseText(file.readText()) as? List<Map<String, Any?>>)

        if (jsonList != null) {
            return jsonList.mapNotNull { MimicEmoji.fromJsonMap(it) }
        }
    }

    return emptyList()
}
