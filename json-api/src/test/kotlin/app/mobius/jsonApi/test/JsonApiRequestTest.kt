package app.mobius.jsonApi.test

import app.mobius.io.ParentPathFile
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.model.request.*
import app.mobius.jsonApi.test.model.request.AttributesMock
import app.mobius.jsonApi.test.model.request.DataAtomicMock
import app.mobius.jsonApi.test.model.request.RelationshipMock
import app.mobius.jsonApi.test.model.request.RelationshipsMock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.util.*

class JsonApiRequestTest {

    private fun writeKtAsJsonToFile(
            moduleName: String = JsonApi.MODULE_NAME_JSON_API,
            parentPathFile: String = ParentPathFile.Test.RESOURCES,
            relPathFile: String,
            value: Any
    ) {
        JsonApi.writeKtAsJsonToFile(
                moduleName = moduleName,
                parentPathFile = parentPathFile,
                relPathFile = relPathFile,
                value = value
        )
    }

    private fun <T> writeJsonAsKtFromFile(
            moduleName: String = JsonApi.MODULE_NAME_JSON_API,
            parentPathFile: String = ParentPathFile.Test.RESOURCES,
            relPathFile: String,
            valueType: Class<T>
    ) = JsonApi.writeJsonAsKtFromFile(
            moduleName = moduleName,
            parentPathFile = parentPathFile,
            relPathFile = relPathFile,
            valueType = valueType
    )

    private fun provideData(type: String = "",
                            id: UUID? = null,
                            attributes: Map<String, Any> = mapOf(),
                            relationships: List<Map<String, Data>> = listOf(),
                            links: Links? = null
    ) = Data(type,id, attributes, relationships, links)

    private fun provideJsonApiRequest(data: List<Data> = listOf()) =
            JsonApiRequest(data = data)

    private fun provideRelationship(
            relationship: Map<String, Data> = mapOf()
    ) = relationship

    private fun provideRelationshipMock(
            anyRelationship: Map<String, DataAtomicMock> = mapOf()
    ) = RelationshipMock(anyRelationship = anyRelationship)

    private fun provideRelationshipsMock(
            relationships: List<Map<String, DataAtomicMock>> = listOf()
    ) = RelationshipsMock(relationships = relationships)

    private fun provideRelationships(
            relationships:  List<Map<String, Data>> = listOf()
    ) : List<Map<String, Data>> = relationships

    @Test
    fun `1 - When write the attributes from json to kt, then shouldn't throw an exception`() {
        val attributesMock = AttributesMock(attributes = mapOf("a" to 1))
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/attributes/attributes.json",
                value = attributesMock
        )
        writeJsonAsKtFromFile(
                relPathFile = "/generated/request/attributes/attributes.json",
                valueType = AttributesMock::class.java
        )
    }

    @Test
    fun `2 - When write relationship mock without a data atomic mock from KT to JSON, Then the expected == actual from JSON as KT and relationship mock is empty`() {
//       Given
        val expectedRelationshipWithoutDataAtomicMock = provideRelationshipMock()

//        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/relationship/withoutDataAtomicMock.json",
                value = expectedRelationshipWithoutDataAtomicMock
        )
        val actualRelationshipWithDataAtomicMock = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/relationship/withoutDataAtomicMock.json",
                valueType = RelationshipMock::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipWithoutDataAtomicMock, actualRelationshipWithDataAtomicMock)
        assert(actualRelationshipWithDataAtomicMock.anyRelationship.isEmpty())
    }

    @Test
    fun `2B - When write relationship mock with a data atomic mock from KT to JSON, Then the expected == actual from JSON as KT and relationship mock is not empty`() {
//       Given
        val expectedRelationshipWithDataAtomicMock = provideRelationshipMock(
                mapOf("profile" to DataAtomicMock())
        )

//        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/relationship/withDataAtomicMock.json",
                value = expectedRelationshipWithDataAtomicMock
        )
        val actualRelationshipWithDataAtomicMock = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/relationship/withDataAtomicMock.json",
                valueType = RelationshipMock::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipWithDataAtomicMock, actualRelationshipWithDataAtomicMock)
        assert(actualRelationshipWithDataAtomicMock.anyRelationship.isNotEmpty())
    }

    @Test
    fun `3A - When write relationships without content from KT as JSON, Then the expected == actual from JSON as KT and relationships is empty`() {
//        Given
        val expectedWithoutRelationships = provideRelationshipsMock()

//        When
        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPathFile = "/generated/request/relationships/withoutContent.json",
                    value = expectedWithoutRelationships
            )
        }
        val actualRelationshipsWithoutDataAtomicMock = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/relationships/withoutContent.json",
                valueType = RelationshipsMock::class.java
        )

//        Then
        Assertions.assertEquals(expectedWithoutRelationships, actualRelationshipsWithoutDataAtomicMock)
        assert(actualRelationshipsWithoutDataAtomicMock.relationships.isEmpty())
    }

    @Test
    fun `3B - When write relationships with each content is empty from KT as JSON, Then the expected == actual from JSON as KT and each content is empty`() {
//        Given
        val expectedRelationshipsWithEachEmptyContent = provideRelationshipsMock(
            relationships = listOf(
                    mapOf(),
                    mapOf(),
            )
        )

//        When
        writeKtAsJsonToFile(
            relPathFile = "/generated/request/relationships/withEachEmptyContent.json",
            value = expectedRelationshipsWithEachEmptyContent
        )
        val actualRelationshipsWithEachEmptyContent = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/relationships/withEachEmptyContent.json",
                valueType = RelationshipsMock::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipsWithEachEmptyContent, actualRelationshipsWithEachEmptyContent)
        assert(expectedRelationshipsWithEachEmptyContent.relationships.first().isEmpty())
    }

    @Test
    fun `3C - When add a relationship with data, Then has a custom name`() {
        val withoutGeneric = RelationshipsMock(
                relationships = listOf(
                        mapOf("profile" to DataAtomicMock()),
                        mapOf("settings" to DataAtomicMock()),
                        mapOf("other" to DataAtomicMock()),
                )
        )

        writeKtAsJsonToFile(
                relPathFile = "/generated/request/relationships/withoutGeneric.json",
                value = withoutGeneric
        )
    }

    @Test
    fun `3D - When write relationships with each content from KT as JSON, Then the expected == actual from JSON as KT and each content is not empty`() {
//        Given
        val expectedRelationshipsWithEachNotEmptyContent = RelationshipsMock(
                relationships = listOf(
                        mapOf("profile" to DataAtomicMock()),
                        mapOf("settings" to DataAtomicMock())
                )
        )

//        When
        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPathFile = "/generated/request/relationships/withEachNotEmptyContent.json",
                    value = expectedRelationshipsWithEachNotEmptyContent
            )
        }
        val actualRelationshipsWithEachNotEmptyContent = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/relationships/withEachNotEmptyContent.json",
                valueType = RelationshipsMock::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipsWithEachNotEmptyContent, actualRelationshipsWithEachNotEmptyContent)
        assert(expectedRelationshipsWithEachNotEmptyContent.relationships.first().isNotEmpty())
    }


    @Test
    fun `4 - When write some links from KT as JSON, Then the expected == actual from JSON as KT`() {
        val expectedLinks = Links()

        writeKtAsJsonToFile(
                relPathFile = "/generated/request/links/links.json",
                value = expectedLinks
        )
        val actualLinks = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/links/links.json",
                valueType = Links::class.java
        )

    //        Then
        Assertions.assertEquals(expectedLinks, actualLinks)
    }

    @Test
    fun `5 - When write a data without relationships from KT as JSON, Then the expected == actual from JSON as KT `() {
        val expectedData = provideData()

        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPathFile = "/generated/request/data/dataWithoutRelationships.json",
                    value = expectedData
            )
        }
        val actualData = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/data/dataWithoutRelationships.json",
                valueType = Data::class.java
        )

    //        Then
        Assertions.assertEquals(expectedData, actualData)
        assert(actualData.relationships.isEmpty())
    }

    @Test
    fun `6 - When write a data with relationships from KT as JSON, Then the expected == actual from JSON as KT and relations is not empty`() {
        val expectedData = provideData(
                relationships = listOf(
                        provideRelationship(),
                        provideRelationship()
                )
        )

        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPathFile = "/generated/request/data/dataWithRelationships.json",
                    value = expectedData
            )
        }
        val actualData = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/data/dataWithRelationships.json",
                valueType = Data::class.java
        )

    //        Then
        Assertions.assertEquals(expectedData, actualData)
        assert(actualData.relationships.isNotEmpty())
    }

    @Test
    fun `7 - When write a jsonApiRequest without data from KT to JSON, Then data of JSON is empty`() {
    //        Given
        val jsonApiRequest = provideJsonApiRequest()

    //        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/jsonApiRequest/emptyData.json",
                value = jsonApiRequest
        )
        val jsonApiRequestActual = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/jsonApiRequest/emptyData.json",
                valueType = JsonApiRequest::class.java
        )

    //        Then
        assert(jsonApiRequestActual.data.isEmpty())
        Assertions.assertEquals(jsonApiRequest, jsonApiRequestActual)
    }


    @Test
    fun `8 - When write a jsonApiRequest with a data and without relationships from KT to JSON, Then data of JSON is not empty and has not relationships`() {
    //        Given
        val expectedJsonApiRequest = provideJsonApiRequest(data = listOf(provideData()))

    //        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/jsonApiRequest/withData.json",
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/jsonApiRequest/withData.json",
                valueType = JsonApiRequest::class.java
        )

    //        Then
        Assertions.assertEquals(expectedJsonApiRequest, actualJsonApiRequest)
        assert(actualJsonApiRequest.data.isNotEmpty())
        assert(actualJsonApiRequest.data.first().relationships.isEmpty())
    }

    @Test
    fun `9 - When write a jsonApiRequest with a data and relationships from KT to JSON, Then data and relationships of JSON is not empty`() {
    //        Given
        val expectedJsonApiRequest = provideJsonApiRequest(
                data = listOf(
                        provideData(
                                relationships = provideRelationships(
                                        listOf(
                                                provideRelationship(),
                                                provideRelationship(),
                                        )
                                )
                        )
                )
        )

    //        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/jsonApiRequest/withDataAndRelationships.json",
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/jsonApiRequest/withDataAndRelationships.json",
                valueType = JsonApiRequest::class.java
        )

//        Then
        Assertions.assertEquals(expectedJsonApiRequest, actualJsonApiRequest)
        assert(actualJsonApiRequest.data.isNotEmpty())
        assert(actualJsonApiRequest.data.first().relationships.isNotEmpty())
    }

    @Test
    fun `10 - When write a jsonApiRequest with data in any relationship from KT to JSON, Then any relationship data of JSON is not empty`() {
    //        Given
        val expectedJsonApiRequest = provideJsonApiRequest(
                data = listOf(
                        provideData(
                                relationships = listOf(
                                        provideRelationship(
                                                relationship = mapOf("data" to provideData(type = "profile"))
                                        ),
                                        provideRelationship(
                                                relationship = mapOf("data" to provideData(type = "settings"))
                                        ),
                                )
                        )
                )
        )

    //        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/jsonApiRequest/withDataInAnyRelationship.json",
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/jsonApiRequest/withDataInAnyRelationship.json",
                valueType = JsonApiRequest::class.java
        )

    //        Then
        Assertions.assertEquals(expectedJsonApiRequest, actualJsonApiRequest)
        assert(actualJsonApiRequest.data.isNotEmpty())
        assert(actualJsonApiRequest.data.first().relationships.isNotEmpty())
        assert(actualJsonApiRequest.data.first().relationships.first().isNotEmpty())
    }
}