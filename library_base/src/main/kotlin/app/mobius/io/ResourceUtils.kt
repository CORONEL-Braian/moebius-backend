package app.mobius.io

import java.io.File

object ResourceUtils {

    /**
     * Create or update a file
     * PRE: File is in data-core module
     * @param canonicalName: e.g: some.json
     * Source: https://stackoverflow.com/a/64084771/5279996
     */
    fun getFile(
            moduleName: String,
            canonicalName: String,
    ): File {
        val absolutePathWithoutCurrentModule = System.getProperty("user.dir")
        val absolutePathFromProjectRoot = "${absolutePathWithoutCurrentModule}/${moduleName}/src/main/resources/$canonicalName"
        println("Absolute Path of some.json: $absolutePathFromProjectRoot") //TODO: Use Logger
        return File(absolutePathFromProjectRoot)
    }
}