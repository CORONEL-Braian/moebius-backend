package app.mobius.infrastructure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class JsonApiTest {

    val canonicalFileName = "some3.json"

    data class SomeTest(val a: String, val b: String) {
        constructor() : this("1", "2")
    }

    data class AttributesTest(val attributes: Map<String, Any>) {
        constructor() : this(attributes = mapOf())
    }

    data class Username(val username: String)



    @Test
    fun writeKtAsJson() {
        val some = SomeTest("2", "2")
        Assertions.assertEquals(
                "{\"a\":\"2\",\"b\":\"2\"}",
                JsonApi.writeKtAsJson(some)
        )
    }

    @Test
    fun writeKtAsJsonToFile() {
        val some = SomeTest("Gaston 2", "Ramiro 2")
        JsonApi.writeKtAsJsonToFile("some.json", some)
    }

    @Test
    fun readJsonAskt() {
//        Given
        val jsonString = "{\"a\":\"4\",\"b\":\"5\"}"
        val expected = SomeTest("4", "5")

        Assertions.assertEquals(
                expected,
                JsonApi.writeJsonAsKt(jsonString, SomeTest::class.java),

        )
    }

    @Test
    fun `write Json As Kt From File`() {
//        Given
        val some = SomeTest("4", "4")
        JsonApi.writeKtAsJsonToFile(canonicalFileName, some)

        val someFromFile = JsonApi.writeJsonAsKtFromFile(canonicalFileName, SomeTest::class.java)

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
    fun `write list of json as kt`() {
        val listJson = "{\n" +
                "  \"attributes\": {\n" +
                "    \"title\": \"JSON:API paints my bikeshed!\",\n" +
                "    \"subtitle\": \"JSON:API paints my bikeshed!\"\n" +
                "  }\n" +
                "}"

        assertDoesNotThrow("read list of json exception") {
            JsonApi.writeJsonAsKtFromFile("attributes.json", AttributesTest::class.java)
        }
    }

}