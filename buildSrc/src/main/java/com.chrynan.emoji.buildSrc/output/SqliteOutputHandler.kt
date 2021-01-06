package com.chrynan.emoji.buildSrc.output

import com.chrynan.emoji.buildSrc.model.MimicEmoji
import com.chrynan.emoji.buildSrc.util.file

/**
 * A helper class responsible for generating a Sqlite file (.sq) that will be used in the "emoji-repo-sqlite" module so
 * that Sqldelight can auto-generate the necessary Kotlin database files.
 *
 * Note that this is for internal use only and not meant to be public. There are hardcoded values which work only for
 * generating code within specific modules of this project.
 */
class SqliteOutputHandler {

    operator fun invoke(input: Input) {
        val file = file(directoryPath = input.directoryPath, fileName = input.fileName)

        if (!file.exists()) file.createNewFile()

        val output = createSqliteOutput(input.emojiLists)

        file.writeText(output)
    }

    private fun createSqliteOutput(emojiLists: List<List<MimicEmoji>>): String {
        val insertText =
            "INSERT INTO emoji (type, unicode, char, name, alias, category, \"group\", icon, uri, static_uri, mime_type)"

        val initialInsertFunctions = emojiLists.mapIndexed { index: Int, emojis: List<MimicEmoji> ->
            val functionName = "initialInsert$index:\n"

            val insertStatements = emojis.joinToString(separator = "\n") { emoji ->
                val unicode = emoji.unicode?.let { "\"$it\"" } ?: "NULL"
                val char = emoji.char?.let { "\"$it\"" } ?: "NULL"
                val icon = emoji.icon?.let { "\"$it\"" } ?: "NULL"
                val category = emoji.category?.let { "\"$it\"" } ?: "NULL"
                val group = emoji.group?.let { "\"$it\"" } ?: "NULL"
                val aliasString = emoji.aliases.joinToString(",") { it.replace(":", "").trim() }
                val uri = emoji.uri?.let { "\"$it\"" } ?: "NULL"
                val staticUri = emoji.static_uri?.let { "\"$it\"" } ?: "NULL"
                val mimeType = emoji.mime_type?.let { "\"$it\"" } ?: "NULL"

                """
                    |   $insertText
                    |   VALUES("${emoji.type}", $unicode, $char, "${emoji.name}", "$aliasString", $category, $group, $icon, $uri, $staticUri, $mimeType);
                """.trimMargin()
            }

            functionName + insertStatements
        }.joinToString(separator = "\n\n")

        return """
            |CREATE TABLE emoji (
            |    type TEXT NOT NULL,
            |    unicode TEXT DEFAULT NULL,
            |    char TEXT DEFAULT NULL,
            |    name TEXT NOT NULL,
            |    alias TEXT NOT NULL,
            |    category TEXT DEFAULT NULL,
            |    "group" TEXT DEFAULT NULL,
            |    icon TEXT DEFAULT NULL,
            |    uri TEXT DEFAULT NULL,
            |    static_uri TEXT DEFAULT NULL,
            |    mime_type TEXT DEFAULT NULL
            |);
            |
            |$initialInsertFunctions
            |
            |getByName:
            |SELECT *
            |FROM emoji
            |WHERE name = ?;
            |
            |getByAlias:
            |SELECT *
            |FROM emoji
            |WHERE alias = ?;
            |
            |getAll:
            |SELECT *
            |FROM emoji;
        """.trimMargin()
    }

    data class Input(
        val directoryPath: String,
        val fileName: String,
        val emojiLists: List<List<MimicEmoji>>
    )
}
