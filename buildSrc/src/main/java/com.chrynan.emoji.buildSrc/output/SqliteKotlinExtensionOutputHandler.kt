package com.chrynan.emoji.buildSrc.output

import com.chrynan.emoji.buildSrc.model.MimicEmoji
import com.chrynan.emoji.buildSrc.util.file

/**
 * A helper class responsible for generating a Kotlin extension function on the
 * com.chrynan.emoji.repo.sqlite.SqliteEmojiRepository class to initialize the database with all of the emojis. This is
 * needed because we had to break up the insert calls programmatically, because there are too many insert calls and
 * they wouldn't fit in a single function.
 *
 * Note that this is for internal use only and not meant to be public. There are hardcoded values which work only for
 * generating code within specific modules of this project.
 */
class SqliteKotlinExtensionOutputHandler {

    operator fun invoke(input: Input) {
        val file = file(directoryPath = input.directoryPath, fileName = input.fileName)

        if (!file.exists()) file.createNewFile()

        val output = createKotlinExtensionOutput(input.packageName, input.emojiLists)

        file.writeText(output)
    }

    private fun createKotlinExtensionOutput(packageName: String, emojiLists: List<List<MimicEmoji>>): String {
        val functionCallStatements = emojiLists.mapIndexed { index: Int, _: List<MimicEmoji> ->
            "|        database.emojiQueries.initialInsert$index()".trimMargin()
        }.joinToString(separator = "\n")

        return """
            |@file:Suppress("unused", "UnusedImport")
            |
            |package $packageName
            |
            |import com.chrynan.emoji.repo.sqlite.SqliteEmojiRepository
            |
            |/**
            | * Initializes this [SqliteEmojiRepository] with the initial data set.
            | *
            | * Note that this should be called before calling any of the other [SqliteEmojiRepository] functions, otherwise the
            | * internal database will be empty and no values can be retrieved.
            | */
            |@Suppress("RedundantSuspendModifier")
            |suspend fun SqliteEmojiRepository.init() {
            |$functionCallStatements
            |}
            |
        """.trimMargin()
    }

    data class Input(
        val directoryPath: String,
        val fileName: String,
        val packageName: String,
        val emojiLists: List<List<MimicEmoji>>
    )
}
