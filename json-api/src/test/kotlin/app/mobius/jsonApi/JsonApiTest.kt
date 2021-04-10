package app.mobius.jsonApi

import app.mobius.jsonApi.test.model.jvm.SomeList
import app.mobius.io.ParentPathFile
import app.mobius.jsonApi.JsonApi.MODULE_NAME_JSON_API
import app.mobius.jsonApi.model.request.Data
import app.mobius.jsonApi.model.request.Links
import app.mobius.jsonApi.test.model.SomeTest
import app.mobius.jsonApi.test.model.request.AttributesMock
import app.mobius.jsonApi.test.model.request.RelationshipMock
import app.mobius.jsonApi.test.model.request.DataAtomicMock
import app.mobius.jsonApi.test.model.request.RelationshipsMock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

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
                    relPathFile = "/jvm/someList.json",
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
                    relPathFile = "/request/attributes.json",
                    valueType = AttributesMock::class.java
            )
        }
    }

    @Test
    fun `7A - Request - When write relationship with a data atomic mock from KT to JSON, then shouldn't throw an exception`() {
        val relationshipWithDataAtomicMock = RelationshipMock(
            mapOf("data" to DataAtomicMock("profile"))
        )
        assertDoesNotThrow {
            JsonApi.writeKtAsJsonToFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/relationshipWithDataAtomicMock.json",
                    value = relationshipWithDataAtomicMock
            )
        }
    }

    @Test
    fun `7B - Request - When write the relationship with a data atomic mock from JSON to KT, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/relationshipWithDataAtomicMock.json",
                    valueType = RelationshipMock::class.java
            )
        }
    }

    @Test
    fun `8A - Request - When write some relationships with a data atomic mock from KT as JSON, then shouldn't throw an exception`() {
        val relationshipsWithDataAtomicMock = RelationshipsMock(
                relationships = listOf(
                        RelationshipMock(
                                mapOf("data" to DataAtomicMock("profile"))
                        ),
                        RelationshipMock(
                                mapOf("data" to DataAtomicMock("setting"))
                        )
                )
        )

        assertDoesNotThrow {
            JsonApi.writeKtAsJsonToFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/relationshipsWithDataAtomicMock.json",
                    value = relationshipsWithDataAtomicMock
            )
        }
    }

    @Test
    fun `8B - Request - When write relationship with a data atomic mock from JSON as KT, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/relationshipsWithDataAtomicMock.json",
                    valueType = RelationshipsMock::class.java
            )
        }
    }

    @Test
    fun `9A - Request - When write some links from KT as JSON, then shouldn't throw an exception`() {
        val links = Links()

        assertDoesNotThrow {
            JsonApi.writeKtAsJsonToFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/links.json",
                    value = links
            )
        }
    }

    @Test
    fun `9A - Request - When write some links from JSON as KT, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/links.json",
                    valueType = Links::class.java
            )
        }
    }

    @Test
    fun `10A - Request - When write a data from KT to JSON, then shouldn't throw an exception`() {
        val data = Data()

        assertDoesNotThrow {
            JsonApi.writeKtAsJsonToFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/data.json",
                    value = data
            )
        }
    }

    @Test
    fun `10B - Request - When write a data from JSON as KT, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/request/data.json",
                    valueType = Data::class.java
            )
        }
    }

}