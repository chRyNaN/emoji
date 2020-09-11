@file:Suppress("unused")

package com.chrynan.emoji.buildSrc.output

import com.chrynan.emoji.buildSrc.model.MimicEmoji
import com.chrynan.emoji.buildSrc.util.file
import com.chrynan.emoji.buildSrc.util.toJson
import com.chrynan.emoji.buildSrc.util.toPrettyJson

/**
 * A helper class for generating JSON output for a List of Emojis.
 *
 * Note that this is for internal use only and not meant to be public. There are hardcoded values which work only for
 * generating code within specific modules of this project.
 */
class JsonOutputHandler {

    operator fun invoke(input: Input) {
        val file = file(directoryPath = input.directoryPath, fileName = input.fileName)

        if (!file.exists()) file.createNewFile()

        val output = createJsonOutput(input.emojis, input.prettyPrint)

        file.writeText(output)
    }

    private fun createJsonOutput(emojis: List<MimicEmoji>, prettyPrint: Boolean): String =
        if (prettyPrint) emojis.toPrettyJson() else emojis.toJson()

    data class Input(
        val directoryPath: String,
        val fileName: String,
        val emojis: List<MimicEmoji>,
        val prettyPrint: Boolean
    )
}
