package app.mobius.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.File

object JsonApiImpl {

    @JvmStatic
    fun main(args: Array<String>) {
        convertJvmToJson()

    }

    fun convertJvmToJson() {
        val objectMapper = ObjectMapper()
        val some = Some("1", "1")
        val someAsString = objectMapper.writeValueAsString(some)
        println(someAsString)

//        Write file
        objectMapper.writeValue(getFile("some.json"), some)
    }

    fun convertJsonToJvm() {
        val json = "{ \"a\" : \"2\", \"b\" : \"2\" }"
        val some = ObjectMapper().readValue(json, Some::class.java)

    }

    /**
     * Get absolute path of file of @param canonicalName
     * PRE: File is in data-core module
     * @param canonicalName: e.g: some.json
     * Source: https://stackoverflow.com/a/64084771/5279996
     */
    private fun getFile(canonicalName: String): File {
        val absolutePathCurrentModule = System.getProperty("user.dir")
        val pathFromProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }
        val absolutePathFromProjectRoot = "${pathFromProjectRoot}Moebius-backend/json-api/src/main/resources/$canonicalName"
        println("Absolute Path of some.json: $absolutePathFromProjectRoot")
        return File(absolutePathFromProjectRoot)
    }



    data class Some(val a: String, val b: String)
}