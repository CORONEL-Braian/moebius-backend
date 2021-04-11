package app.mobius.io

import java.io.File

object ParentPathFile {
    private const val COMMON_KOTLIN = "/kotlin"
    private const val COMMON_RESOURCES = "/resources"
    object Main {
        private const val MAIN = "/main"
        const val KOTLIN = "${MAIN}${COMMON_KOTLIN}"
        const val RESOURCES = "${MAIN}${COMMON_RESOURCES}"
    }
    object Test {
        private const val TEST = "/test"
        const val KOTLIN = "${TEST}${COMMON_KOTLIN}"
        const val RESOURCES = "${TEST}${COMMON_RESOURCES}"
    }
}

object ResourceUtils {

    /**
     * Create or update a file
     * PRE: File is in data-core module
     * @param relPath: Relative to ${moduleName}/src/${srcType}/resources/
     * Source: https://stackoverflow.com/a/64084771/5279996
     */
    fun getFile(
            moduleName: String,
            parentPath: String,
            relPath: String
    ): File {
        val absolutePathCurrentModule = System.getProperty("user.dir")
        val absolutePathProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }

        val absolutePathOfFile = "${absolutePathProjectRoot}${moduleName}/src${parentPath}$relPath"
        println("Absolute Path of file: $absolutePathOfFile") //TODO: Use Logger
        return File(absolutePathOfFile)
    }
}