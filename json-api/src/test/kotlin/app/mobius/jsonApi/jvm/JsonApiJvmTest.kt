package app.mobius.jsonApi.jvm

import app.mobius.io.ParentPath
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.model.jvm.SomeList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
class JsonApiJvmTest {


    @Test
    fun `1 - When write ktAsJsonToFile of some list, then json is formatted as pretty`() {
//        Then
        val someList = SomeList(map = mapOf(
                "a" to 1,
                "b" to 2,
        ))
        val moduleName = JsonApi.MODULE_NAME_JSON_API
        val parentPath = ParentPath.Test.RESOURCES
        val relPath = "/generated/jvm/jsonFormatted.json"

//        When
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = relPath,
                value = someList
        )

        Assertions.assertEquals(
                "{\n" +
                        "  \"map\": {\n" +
                        "    \"a\": 1,\n" +
                        "    \"b\": 2\n" +
                        "  }\n" +
                        "}",
                JsonApi.readText(
                        moduleName = moduleName,
                        parentPath = parentPath,
                        relPath = relPath
                )
        )
    }

    @Test
    fun `2 - When write ktAsJsonToFile some list, Then someList == jsonAsKtFromFile and b is expected in a`() {
//        Given
        val expectedSomeList = SomeList(map = mapOf("a" to "b"))
        val relPath = "/generated/jvm/json"

        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = relPath,
                value = expectedSomeList
        )

        val actualSomeList = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = relPath,
                valueType = SomeList::class.java
        )

        Assertions.assertEquals(
                actualSomeList,
                expectedSomeList
        )
        Assertions.assertEquals("b", actualSomeList.map["a"])
    }

}