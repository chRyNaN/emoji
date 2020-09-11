package com.chrynan.emoji.buildSrc.util

import java.io.File

fun file(directoryPath: String, fileName: String): File {
    val fullPathName =
        if (directoryPath.endsWith("/")) "${directoryPath}${fileName}" else "${directoryPath}/${fileName}"

    return File(fullPathName)
}