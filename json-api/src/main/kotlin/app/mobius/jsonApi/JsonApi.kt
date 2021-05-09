package app.mobius.jsonApi

import app.mobius.io.ResourceUtils
import app.mobius.io.ResourceUtils.getFile
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.io.path.writeText

@ExperimentalPathApi
object JsonApi {

    const val MODULE_NAME_JSON_API = "json-api"

    private val prettyGson: Gson = GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create()

    /**
     * Write class kotlin as pretty json
     */
    fun writeKtAsJson(value: Any) : String {
        val objectMapper = ObjectMapper()
        val json = objectMapper.writeValueAsString(value)

        val jsonElement = prettyGson.fromJson(json, JsonElement::class.java)
        return prettyGson.toJson(jsonElement)
    }

    /**
     * Write a kotlin class as json in file
     * @param relPath: some.json
     */
    fun writeKtAsJsonToFile(
            moduleName: String,
            parentPath: String,
            relPath: String,
            value: Any
    ) {
        Path(
                ResourceUtils.buildAbsolutePath(
                        moduleName = moduleName,
                        parentPath = parentPath,
                        relPath = relPath
                )
        )
                .writeText(
                        writeKtAsJson(value)
                )
    }

    /**
     * Databind from json to KT
     * Precondition: Entities have secondary constructor
     */
    fun <T> writeJsonAsKt(jsonString: String, valueType: Class<T>): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(jsonString, valueType)
    }

    /**
     * Precondition: Entities have secondary constructor
     */
    fun <T> writeJsonAsKtFromFile(moduleName: String,
                                  parentPath: String,
                                  relPath: String,
                                  valueType: Class<T>
    ): T {
        return ObjectMapper().readValue(
                getFile(moduleName = moduleName, parentPath = parentPath, relPath = relPath),
                valueType
        )
    }

    fun readText(
            moduleName: String,
            parentPath: String,
            relPath: String,
    ): String {
        return Path( ResourceUtils.buildAbsolutePath(
                moduleName = moduleName,
                parentPath = parentPath,
                relPath = relPath
        )).readText()
    }

}