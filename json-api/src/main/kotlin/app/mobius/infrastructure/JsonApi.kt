package app.mobius.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File


// JSON   KT
object JsonApi {

    fun writeKtAsJson(value: Any) : String {
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(value)
    }

    /**
     * @param canonicalFileName: some.json
     */
    fun writeKtAsJsonToFile(canonicalFileName: String, value: Any) {
        val objectMapper = ObjectMapper()
        objectMapper.writeValue(getFile(canonicalFileName), value)
    }

    /**
     * Get absolute path of file of @param canonicalName
     * PRE: File is in data-core module
     * @param canonicalName: e.g: some.json
     * Source: https://stackoverflow.com/a/64084771/5279996
     */
    fun getFile(canonicalName: String): File {
        val absolutePathCurrentModule = System.getProperty("user.dir")
        val pathFromProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }
        val absolutePathFromProjectRoot = "${pathFromProjectRoot}json-api/src/main/resources/$canonicalName"
        println("Absolute Path of some.json: $absolutePathFromProjectRoot") //TODO: Use Logger
        return File(absolutePathFromProjectRoot)
    }

    /**
     * Precondition: Entities have secondary constructor
     */
    fun <T> readJsonAsKt(jsonString: String, t: Class<T>): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(jsonString, t)
    }

    /**
     * Precondition: Entities have secondary constructor
     */
    fun <T> readJsonAsJvmFromFile(canonicalFileName: String, t: Class<T>): T {
        return ObjectMapper().readValue(getFile(canonicalFileName), t)
    }

}