package app.mobius.infrastructure

import app.mobius.io.ResourceUtils.getFile
import com.fasterxml.jackson.databind.ObjectMapper

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
        objectMapper.writeValue(getFile("json-api", canonicalFileName,), value)
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
    fun <T> writeJsonAsKtFromFile(canonicalFileName: String, t: Class<T>): T {
//        return ObjectMapper().readValue(getFile("json-api", canonicalFileName), t)
        return ObjectMapper().readValue(getFile("json-api", canonicalFileName), t)
    }

}