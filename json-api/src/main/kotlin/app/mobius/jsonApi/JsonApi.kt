package app.mobius.jsonApi

import app.mobius.io.ResourceUtils.getFile
import app.mobius.io.ParentPathFile
import com.fasterxml.jackson.databind.ObjectMapper

object JsonApi {

    const val MODULE_NAME_JSON_API = "json-api"

    fun writeKtAsJson(value: Any) : String {
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(value)
    }

    /**
     * Write a kotlin class as json in file
     * @param relPathFile: some.json
     */
    fun writeKtAsJsonToFile(
            moduleName: String,
            parentPathFile: String,
            relPathFile: String,
            value: Any
    ) {
        val objectMapper = ObjectMapper()
        objectMapper.writeValue(
                getFile(
                    moduleName = moduleName,
                    parentPath = parentPathFile,
                    relPath = relPathFile
                ),
                value
        )
    }

    /**
     * Databind from json to KT
     * Precondition: Entities have secondary constructor
     */
    fun <T> writeJsonAsKt(jsonString: String, t: Class<T>): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(jsonString, t)
    }

    /**
     * Precondition: Entities have secondary constructor
     */
    fun <T> writeJsonAsKtFromFile(moduleName: String,
                                  parentPathFile: String,
                                  relPathFile: String,
                                  valueType: Class<T>
    ): T {
        return ObjectMapper().readValue(
                getFile(moduleName = moduleName, parentPath = parentPathFile, relPath = relPathFile),
                valueType
        )
    }

}