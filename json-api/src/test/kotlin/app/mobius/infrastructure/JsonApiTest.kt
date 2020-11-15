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
        val some = SomeTest("1", "1")
        Assertions.assertEquals(JsonApi.writeKtAsJson(some), "{\"a\":\"1\",\"b\":\"1\"}")
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
        val some = SomeTest("2", "2")
        JsonApi.writeKtAsJsonToFile("some.json", some)
    }

    @Test
    fun readJsonToJvm() {
//        Given
        val some = SomeTest("3", "3")
        val jsonString = "{\"a\":\"3\",\"b\":\"3\"}"

        Assertions.assertEquals(
                JsonApi.readJsonAsJvm(jsonString, SomeTest::class.java),
                some
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