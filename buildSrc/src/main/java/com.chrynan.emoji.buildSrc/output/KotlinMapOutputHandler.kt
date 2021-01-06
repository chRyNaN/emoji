package com.chrynan.emoji.buildSrc.output

import com.chrynan.emoji.buildSrc.model.MimicEmoji
import java.io.File

/**
 * A helper class responsible for generating a data set initializer extension function on the
 * com.chrynan.emoji.repo.map.KotlinMapEmojiRepository class. This function would be responsible for loading the emoji
 * data into the internal [Map] used for that repository.
 *
 * Note that this is for internal use only and not meant to be public. There are hardcoded values which work only for
 * generating code within specific modules of this project.
 */
class KotlinMapOutputHandler {

    operator fun invoke(input: Input) {
        val outputFile = File("${input.directoryPath}/${input.fileName}")

        if (!outputFile.exists()) outputFile.createNewFile()

        val output = createKotlinExtensionOutput(input.packageName, input.emojiLists)

        outputFile.writeText(output)
    }

    private fun createKotlinExtensionOutput(packageName: String, emojiLists: List<List<MimicEmoji>>): String {
        val mapInitializers = emojiLists.mapIndexed { index: Int, _: List<MimicEmoji> ->
            "|    emojiList$index.associateByTo(map) { it.name }".trimMargin()
        }.joinToString(separator = "\n")

        val lists = emojiLists.mapIndexed { index: Int, emojis: List<MimicEmoji> ->
            val initializations = emojis.joinToString(separator = ",\n") { emoji ->
                val icon = if (emoji.icon == null) "null" else "\"${emoji.icon}\""
                val category = if (emoji.category == null) "null" else "\"${emoji.category}\""
                val group = if (emoji.group == null) "null" else "\"${emoji.group}\""
                val aliasString = if(emoji.alias.isEmpty()) "emptyList()" else "listOf(${emoji.alias.joinToString(",") { "\"$it\"" }})"

                "|      Emoji(unicodeString = \"${emoji.unicode}\", char = \"${emoji.char}\", name = \"${emoji.name}\", aliases = $aliasString, category = $category, group = $group, iconUri = $icon)".trimMargin()
            }

            """
            |private val emojiList$index: List<Emoji>
            |   get() = listOf(
            |       $initializations
            |)
            """.trimMargin()
        }.joinToString(separator = "\n\n")

        return """
            |@file:Suppress("unused", "UnusedImport")
            |
            |package $packageName
            |
            |import com.chrynan.emoji.core.Emoji
            |import com.chrynan.emoji.repo.map.KotlinMapEmojiRepository
            |
            |/**
            | * Initializes this [KotlinMapEmojiRepository] with the initial data set.
            | *
            | * Note that this should be called before calling any of the other [KotlinMapEmojiRepository] functions, otherwise 
            | * the internal data set will be empty and no values can be retrieved.
            | */
            |@Suppress("RedundantSuspendModifier")
            |suspend fun KotlinMapEmojiRepository.init() {
            |$mapInitializers
            |}
            |
            |$lists
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
