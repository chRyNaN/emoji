package com.chrynan.emoji.buildSrc.extension

import com.chrynan.emoji.buildSrc.plugin.KotlinMapGeneratorPlugin

/**
 * Represents a Gradle Extension for the [KotlinMapGeneratorPlugin] that can be overloaded to change the default values
 * used in the plugin.
 */
open class KotlinMapGeneratorExtension {

    companion object {

        const val EXTENSION_NAME = "kotlinMapGenerator"
    }

    var inputJsonFilePath: String? = null
    var outputKotlinPath: String? = null
    var kotlinPackageName: String? = null
}
