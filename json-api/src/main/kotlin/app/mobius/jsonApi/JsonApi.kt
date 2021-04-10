package app.mobius.jsonApi

import app.mobius.io.ResourceUtils
import app.mobius.io.ResourceUtils.getFile
import app.mobius.io.SrcType
import com.fasterxml.jackson.databind.ObjectMapper

object JsonApi {

    const val MODULE_NAME_JSON_API = "json-api"

    fun writeKtAsJson(value: Any) : String {
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(value)
    }

    /**
     * @param canonicalFileName: some.json
     */
    fun writeKtAsJsonToFile(canonicalFileName: String, value: Any) {
        val objectMapper = ObjectMapper()
        objectMapper.writeValue(getFile(moduleName = "json-api", relPathFile = canonicalFileName,), value)
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
                                  srcType: String = SrcType.MAIN,
                                  relPathFile: String,
                                  valueType: Class<T>
    ): T {
        return ObjectMapper().readValue(
                getFile(moduleName = moduleName, srcType = srcType, relPathFile = relPathFile),
                valueType
        )
    }

}