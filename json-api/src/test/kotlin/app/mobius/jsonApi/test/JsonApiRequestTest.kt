package app.mobius.jsonApi.test

import app.mobius.io.ParentPathFile
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.model.request.*
import app.mobius.jsonApi.test.model.request.AttributesMock
import app.mobius.jsonApi.test.model.request.RelationshipDataFake
import app.mobius.jsonApi.test.model.request.RelationshipFake
import app.mobius.jsonApi.test.model.request.RelationshipsFake
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

    private fun provideJsonApiRequest(
            data: List<RequestData> = listOf()
    ) = JsonApiRequest(
            data = data
    )

    private fun provideRequestData(type: String = "",
                                   id: UUID? = null,
                                   attributes: Map<String, Any> = mapOf(),
                                   relationships: List<Map<String, RelationshipData>> = listOf(),
                                   links: Links? = null
    ) = RequestData(
            type = type,
            id = id,
            attributes = attributes,
            relationships = relationships,
            links = links
    )

    private fun provideRelationshipMock(
            relationship: Map<String, RelationshipData> = mapOf()
    ): Map<String, RelationshipData> = relationship

    private fun provideRelationshipFake(
            anyRelationship: Map<String, RelationshipDataFake> = mapOf()
    ) = RelationshipFake(anyRelationship = anyRelationship)

    private fun provideRelationshipsFake(
            relationships: List<Map<String, RelationshipDataFake>> = listOf()
    ) = RelationshipsFake(relationships = relationships)

    private fun provideRelationshipData(requestData: RequestData = RequestData()) = RelationshipData(requestData)

    private fun provideRelationships(
            relationships:  List<Map<String, RelationshipData>> = listOf()
    ) : List<Map<String, RelationshipData>> = relationships

    @Test
    fun `1 - When write the attributes mock from ktAsJson, Then the expected == actual from jsonAsKt and isNotEmpty`() {
        val expectedAttributesMock = AttributesMock(attributes = mapOf("a" to 1))
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPathFile = ParentPathFile.Test.RESOURCES,
                relPathFile = "/generated/request/attributes/notEmpty.json",
                value = expectedAttributesMock
        )
        val actualAttributesMock = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/attributes/notEmpty.json",
                valueType = AttributesMock::class.java
        )
        Assertions.assertEquals(expectedAttributesMock, actualAttributesMock)
        assert(expectedAttributesMock.attributes.isNotEmpty())
    }

    @Test
    fun `2 - When write relationship mock without a data atomic mock from KT to JSON, Then the expected == actual from JSON as KT and relationship mock is empty`() {
//       Given
        val expectedRelationshipWithoutDataFake = provideRelationshipFake()

//        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/relationship/withoutDataFake.json",
                value = expectedRelationshipWithoutDataFake
        )
        val actualRelationshipWithDataFake = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/relationship/withoutDataFake.json",
                valueType = RelationshipFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipWithoutDataFake, actualRelationshipWithDataFake)
        assert(actualRelationshipWithDataFake.anyRelationship.isEmpty())
    }

    @Test
    fun `2B - When write relationship mock with a data atomic mock from KT to JSON, Then the expected == actual from JSON as KT and relationship mock is not empty`() {
//       Given
        val expectedRelationshipWithDataFake = provideRelationshipFake(
                mapOf("profile" to RelationshipDataFake())
        )

//        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/relationship/withDataFake.json",
                value = expectedRelationshipWithDataFake
        )
        val actualRelationshipWithDataFake = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/relationship/withDataFake.json",
                valueType = RelationshipFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipWithDataFake, actualRelationshipWithDataFake)
        assert(actualRelationshipWithDataFake.anyRelationship.isNotEmpty())
    }

    @Test
    fun `3A - When write relationships without content from KT as JSON, Then the expected == actual from JSON as KT and relationships is empty`() {
//        Given
        val expectedWithoutRelationships = provideRelationshipsFake()

//        When
        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPathFile = "/generated/request/relationships/withoutContent.json",
                    value = expectedWithoutRelationships
            )
        }
        val actualRelationshipsWithoutDataFake = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/relationships/withoutContent.json",
                valueType = RelationshipsFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedWithoutRelationships, actualRelationshipsWithoutDataFake)
        assert(actualRelationshipsWithoutDataFake.relationships.isEmpty())
    }

    @Test
    fun `3B - When write relationships with each content is empty from KT as JSON, Then the expected == actual from JSON as KT and each content is empty`() {
//        Given
        val expectedRelationshipsWithEachEmptyContent = provideRelationshipsFake(
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
                valueType = RelationshipsFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipsWithEachEmptyContent, actualRelationshipsWithEachEmptyContent)
        assert(expectedRelationshipsWithEachEmptyContent.relationships.first().isEmpty())
    }

    @Test
    fun `3C - When add a relationship with data, Then has a custom name`() {
        val withoutGeneric = RelationshipsFake(
                relationships = listOf(
                        mapOf("profile" to RelationshipDataFake()),
                        mapOf("settings" to RelationshipDataFake()),
                        mapOf("other" to RelationshipDataFake()),
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
        val expectedRelationshipsWithEachNotEmptyContent = RelationshipsFake(
                relationships = listOf(
                        mapOf("profile" to RelationshipDataFake()),
                        mapOf("settings" to RelationshipDataFake())
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
                valueType = RelationshipsFake::class.java
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
        val expectedData = provideRequestData()

        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPathFile = "/generated/request/data/dataWithoutRelationships.json",
                    value = expectedData
            )
        }
        val actualData = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/data/dataWithoutRelationships.json",
                valueType = RequestData::class.java
        )

    //        Then
        Assertions.assertEquals(expectedData, actualData)
        assert(actualData.relationships.isEmpty())
    }

    @Test
    fun `6 - When write a data with relationships from KT as JSON, Then the expected == actual from JSON as KT and relations is not empty`() {
        val expectedDataWithRelationships = provideRequestData(
                relationships = listOf(
                        mapOf("profile" to provideRelationshipData()),
                        mapOf("settings" to provideRelationshipData()),
                )
        )

        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPathFile = "/generated/request/data/dataWithRelationships.json",
                    value = expectedDataWithRelationships
            )
        }
        val actualData = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/data/dataWithRelationships.json",
                valueType = RequestData::class.java
        )

    //        Then
        Assertions.assertEquals(expectedDataWithRelationships, actualData)
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
        val expectedJsonApiRequest = provideJsonApiRequest(data = listOf(provideRequestData()))

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
                        provideRequestData(
                                relationships = provideRelationships(
                                        listOf(
                                                provideRelationshipMock(),
                                                provideRelationshipMock(),
                                        )
                                )
                        )
                )
        )

    //        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/jsonApiRequest/withDataAndEmptyRelationships.json",
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/jsonApiRequest/withDataAndEmptyRelationships.json",
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
                        provideRequestData(
                                relationships = listOf(
                                        mapOf("profile" to provideRelationshipData()),
                                        mapOf("settings" to provideRelationshipData()),
                                )
                        )
                )
        )

    //        When
        writeKtAsJsonToFile(
                relPathFile = "/generated/request/jsonApiRequest/withDataAndNotEmptyRelationships.json",
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPathFile = "/generated/request/jsonApiRequest/withDataAndNotEmptyRelationships.json",
                valueType = JsonApiRequest::class.java
        )

    //        Then
        Assertions.assertEquals(expectedJsonApiRequest, actualJsonApiRequest)
        assert(actualJsonApiRequest.data.isNotEmpty())
        assert(actualJsonApiRequest.data.first().relationships.isNotEmpty())
        assert(actualJsonApiRequest.data.first().relationships.first().isNotEmpty())
    }

}