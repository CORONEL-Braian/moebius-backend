package app.mobius.jsonApi.test

import app.mobius.io.ParentPathFile
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.test.model.jvm.SomeList
import org.junit.jupiter.api.Test
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
class JsonApiJvmTest {

    @Test
    fun `1 - When write a some list from json to kt, then shouldn't throw an exception`() {
        val someList = SomeList(map = mapOf("a" to 1))
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/jvm/someList.json",
                value = someList
        )
        JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/jvm/someList.json",
                valueType = SomeList::class.java
        )
    }

}