package com.chrynan.emoji.buildSrc.extension

import com.chrynan.emoji.buildSrc.plugin.SqliteGeneratorPlugin

/**
 * Represents a Gradle Extension for the [SqliteGeneratorPlugin] that can be overloaded to change the default values
 * used in the plugin.
 */
open class SqliteGeneratorExtension {

    companion object {

        const val EXTENSION_NAME = "sqliteGenerator"
    }

    var inputJsonFilePath: String? = null
    var outputSqlitePath: String? = null
    var outputKotlinPath: String? = null
    var kotlinPackageName: String? = null
}
