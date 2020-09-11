package com.chrynan.emoji.buildSrc.plugin

import com.chrynan.emoji.buildSrc.extension.KotlinMapGeneratorExtension
import com.chrynan.emoji.buildSrc.output.KotlinMapOutputHandler
import com.chrynan.emoji.buildSrc.util.getMimicEmojisFromFilePath
import com.chrynan.emoji.buildSrc.util.partitionToSmallerList
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class KotlinMapGeneratorPlugin : Plugin<Project> {

    companion object {

        private const val TASK_NAME = "generateEmojiKotlin"
    }

    private val generateKotlinMapOutput = KotlinMapOutputHandler()

    override fun apply(target: Project) {
        val extension =
            target.extensions.create<KotlinMapGeneratorExtension>(KotlinMapGeneratorExtension.EXTENSION_NAME)

        val inputJsonPath = extension.inputJsonFilePath ?: "${target.projectDir}/src/commonMain/resources/emojis.json"
        val outputKotlinPath =
            extension.outputKotlinPath ?: "${target.projectDir}/src/commonMain/kotlin/com.chrynan.emoji.repo.map/"
        val kotlinPackageName = extension.kotlinPackageName ?: "com.chrynan.emoji.repo.map"

        val kotlinFileName = "KotlinMapEmojiRepositoryExtensions.kt"

        target.tasks.register(TASK_NAME) {
            group = EmojiPluginConstants.GROUP_NAME

            doFirst {
                val emojiLists = getMimicEmojisFromFilePath(filePath = inputJsonPath).partitionToSmallerList()

                val input = KotlinMapOutputHandler.Input(
                    directoryPath = outputKotlinPath,
                    fileName = kotlinFileName,
                    packageName = kotlinPackageName,
                    emojiLists = emojiLists
                )

                generateKotlinMapOutput(input)
            }
        }
    }
}
