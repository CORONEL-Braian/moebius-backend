package app.mobius.jsonApi

import app.mobius.jsonApi.test.model.jvm.SomeList
import app.mobius.io.ParentPathFile
import app.mobius.jsonApi.JsonApi.MODULE_NAME_JSON_API
import app.mobius.jsonApi.test.model.SomeTest
import app.mobius.jsonApi.test.model.request.AttributesMock
import app.mobius.jsonApi.test.model.request.RelationshipValueWithStringMock
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
        val some = SomeTest("3", "4")
        JsonApi.writeKtAsJsonToFile(
                moduleName = MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/temp/some.json",
                value = some)
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
        val someClass = SomeTest("4", "4")
        JsonApi.writeKtAsJsonToFile(
                moduleName = MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Main.RESOURCES,
                relPathFile = canonicalFileName,
                value = someClass
        )

        val someFromFile = JsonApi.writeJsonAsKtFromFile(
                moduleName = MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Main.RESOURCES,
                relPathFile = canonicalFileName,
                valueType = SomeTest::class.java
        )

        Assertions.assertEquals(
                someFromFile,
                someClass
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
                    parentPathFile = ParentPathFile.Test.RESOURCES,
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
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "request/attributes.json",
                    valueType = AttributesMock::class.java
            )
        }
    }

    @Test
    fun `7 - Request - When write relationship value with string from KT to JSON, then shouldn't throw an exception`() {
        val relationshipValueWithStringMock = RelationshipValueWithStringMock(
            listOf("1", "2")
        )
        assertDoesNotThrow {
            JsonApi.writeKtAsJsonToFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/relationshipValueWithStringMock.json",
                    value = relationshipValueWithStringMock
            )
        }
    }

    @Test
    fun `8 - Request - When write the relationship value with string from JSON to KT, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/relationshipValueWithStringMock.json",
                    valueType = RelationshipValueWithStringMock::class.java
            )
        }
    }

}