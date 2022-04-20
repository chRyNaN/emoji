package com.chrynan.emoji.buildSrc.plugin

import com.chrynan.emoji.buildSrc.extension.SqliteGeneratorExtension
import com.chrynan.emoji.buildSrc.output.SqliteKotlinExtensionOutputHandler
import com.chrynan.emoji.buildSrc.output.SqliteOutputHandler
import com.chrynan.emoji.buildSrc.util.getMimicEmojisFromFilePath
import com.chrynan.emoji.buildSrc.util.partitionToSmallerList
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

/**
 * A Gradle [Plugin] used to generate a Sqlite (.sq) file that can be read by SqlDelight and a corresponding Kotlin
 * extension file that calls some SqlDelight generated code to initialize the database.
 *
 * Note that this is for internal use only and not meant to be public. There are hardcoded values which work only for
 * generating code within specific modules of this project.
 */
class SqliteGeneratorPlugin : Plugin<Project> {

    companion object {

        private const val TASK_NAME = "generateEmojiSqlite"
    }

    private val generateSqliteOutput = SqliteOutputHandler()
    private val generateSqliteKotlinOutput = SqliteKotlinExtensionOutputHandler()

    override fun apply(target: Project) {
        val extension = target.extensions.create<SqliteGeneratorExtension>(SqliteGeneratorExtension.EXTENSION_NAME)

        val inputJsonPath = extension.inputJsonFilePath ?: "${target.projectDir}/src/commonMain/resources/emojis.json"
        val outputSqlitePath = extension.outputSqlitePath
            ?: "${target.projectDir}/src/commonMain/sqldelight/com/chrynan/emoji/repo/sqlite/"
        val outputKotlinPath =
            extension.outputKotlinPath ?: "${target.projectDir}/src/commonMain/kotlin/com.chrynan.emoji.repo.sqlite/"
        val kotlinPackageName = extension.kotlinPackageName ?: "com.chrynan.emoji.repo.sqlite"

        val sqliteFileName = "Emoji.sq"
        val kotlinFileName = "SqliteEmojiRepositoryExtensions.kt"

        target.tasks.register(TASK_NAME) {
            group = EmojiPluginConstants.GROUP_NAME

            doFirst {
                val emojiLists = getMimicEmojisFromFilePath(filePath = inputJsonPath).partitionToSmallerList()

                val sqliteInput = SqliteOutputHandler.Input(
                    directoryPath = outputSqlitePath,
                    fileName = sqliteFileName,
                    emojiLists = emojiLists
                )

                val kotlinInput = SqliteKotlinExtensionOutputHandler.Input(
                    directoryPath = outputKotlinPath,
                    fileName = kotlinFileName,
                    packageName = kotlinPackageName,
                    emojiLists = emojiLists
                )

                generateSqliteOutput(sqliteInput)
                generateSqliteKotlinOutput(kotlinInput)
            }
        }
    }
}
