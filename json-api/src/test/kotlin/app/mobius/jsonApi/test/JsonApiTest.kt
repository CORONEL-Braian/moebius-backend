package app.mobius.jsonApi.test

import app.mobius.io.ParentPathFile
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.JsonApi.MODULE_NAME_JSON_API
import app.mobius.jsonApi.test.model.SomeTest
import app.mobius.jsonApi.test.model.jvm.OldPropertyName
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JsonApiTest {

    private val canonicalFileName = "/some3.json"

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
                relPathFile = "/generated/temp/some.json",
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
    fun `When change property name from KT to JSON, Then the json file shoul show the new property name`() {
        val propertyName = OldPropertyName("")
        JsonApi.writeKtAsJsonToFile(
                moduleName = MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/jvm/newPropertyName.json",
                value = propertyName
        )
    }

}