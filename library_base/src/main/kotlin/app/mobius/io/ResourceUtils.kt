package app.mobius.io

import app.mobius.io.SrcType.MAIN
import java.io.File

object SrcType {
    const val MAIN = "main"
    const val TEST = "test"
}

object ResourceUtils {

    /**
     * Create or update a file
     * PRE: File is in data-core module
     * @param relPathFile: Relative to ${moduleName}/src/${srcType}/resources/
     * Source: https://stackoverflow.com/a/64084771/5279996
     */
    fun getFile(
            moduleName: String,
            srcType: String = MAIN,
            relPathFile: String
    ): File {
        val absolutePathCurrentModule = System.getProperty("user.dir")
        val absolutePathProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }

        val absolutePathOfFile = "${absolutePathProjectRoot}${moduleName}/src/${srcType}/resources/$relPathFile"
        println("Absolute Path of some.json: $absolutePathOfFile") //TODO: Use Logger
        return File(absolutePathOfFile)
    }
}