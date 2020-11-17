package app.mobius.infrastructure

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class JsonApiTest {

    data class SomeTest(val a: String, val b: String) {
        constructor() : this("1", "2")
    }

    @Test
    fun writeKtAsJson() {
        val some = SomeTest("2", "2")
        Assertions.assertEquals(
                "{\"a\":\"2\",\"b\":\"2\"}",
                JsonApi.writeKtAsJson(some)
        )
    }

    @Test
    fun getFile() {
        val canonicalName = "some.json"

        val absolutePathCurrentModule = System.getProperty("user.dir")
        val pathFromProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }

        val actual = JsonApi.getFile(canonicalName)
        val expected = File("${pathFromProjectRoot}json-api/src/main/resources/${canonicalName}")

        Assertions.assertEquals(actual, expected)
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
                JsonApi.readJsonAsKt(jsonString, SomeTest::class.java),

        )
    }

    @Test
    fun `readJsonAsJvmFromFile`() {
//        Given
        val some = SomeTest("4", "4")
        val canonicalFileName = "some.json"

        JsonApi.writeKtAsJsonToFile(canonicalFileName, some)

        Assertions.assertEquals(
                JsonApi.readJsonAsJvmFromFile(canonicalFileName, SomeTest::class.java),
                some
        )
    }

}