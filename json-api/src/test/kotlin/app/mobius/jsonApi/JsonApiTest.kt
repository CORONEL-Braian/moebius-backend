package app.mobius.jsonApi

import app.mobius.jsonApi.model.jvm.SomeList
import app.mobius.io.SrcType
import app.mobius.jsonApi.JsonApi.MODULE_NAME_JSON_API
import app.mobius.jsonApi.model.SomeTest
import app.mobius.jsonApi.model.request.AttributesTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class JsonApiTest {

    private val canonicalFileName = "some3.json"

    @Test
    fun `1 - writeKtAsJson`() {
        val some = SomeTest("2", "2")
        Assertions.assertEquals(
                "{\"a\":\"2\",\"b\":\"2\"}",
                JsonApi.writeKtAsJson(some)
        )
    }

    @Test
    fun `2 - writeKtAsJsonToFile`() {
        val some = SomeTest("Gaston 2", "Ramiro 2")
        JsonApi.writeKtAsJsonToFile("some.json", some)
    }

    @Test
    fun `3 - readJsonAskt`() {
//        Given
        val jsonString = "{\"a\":\"4\",\"b\":\"5\"}"
        val expected = SomeTest("4", "5")

        Assertions.assertEquals(
                expected,
                JsonApi.writeJsonAsKt(jsonString, SomeTest::class.java),

        )
    }

    @Test
    fun `4 - write Json As Kt From File`() {
//        Given
        val some = SomeTest("4", "4")
        JsonApi.writeKtAsJsonToFile(canonicalFileName, some)

        val someFromFile = JsonApi.writeJsonAsKtFromFile(
                moduleName = MODULE_NAME_JSON_API,
                relPathFile = canonicalFileName,
                valueType = SomeTest::class.java
        )

        Assertions.assertEquals(
                someFromFile,
                some
        )
        Assertions.assertEquals("4", someFromFile.a)

//        --------------

        val new = SomeTest()
        Assertions.assertEquals("1", new.a)
    }

    @Test
    fun `5 - JVM - When write a some list from json to kt, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = MODULE_NAME_JSON_API,
                    srcType = SrcType.TEST,
                    relPathFile = "jvm/someList.json",
                    valueType = SomeList::class.java
            )
        }
    }

    @Test
    fun `6 - Request - When write the attributes from json to kt, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = MODULE_NAME_JSON_API,
                    srcType = SrcType.TEST,
                    relPathFile = "request/attributes.json",
                    valueType = AttributesTest::class.java
            )
        }
    }

}