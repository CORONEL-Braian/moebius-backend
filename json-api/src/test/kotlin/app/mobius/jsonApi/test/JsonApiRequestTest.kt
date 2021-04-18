package app.mobius.jsonApi.test

import app.mobius.io.ParentPath
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
import  kotlin.io.path.*

@ExperimentalPathApi
class JsonApiRequestTest {

    private fun writeKtAsJsonToFile(
            moduleName: String = JsonApi.MODULE_NAME_JSON_API,
            parentPath: String = ParentPath.Test.RESOURCES,
            relPath: String,
            value: Any
    ) {
        JsonApi.writeKtAsJsonToFile(
                moduleName = moduleName,
                parentPath = parentPath,
                relPath = relPath,
                value = value
        )
    }

    private fun <T> writeJsonAsKtFromFile(
            moduleName: String = JsonApi.MODULE_NAME_JSON_API,
            parentPath: String = ParentPath.Test.RESOURCES,
            relPath: String,
            valueType: Class<T>
    ) = JsonApi.writeJsonAsKtFromFile(
            moduleName = moduleName,
            parentPath = parentPath,
            relPath = relPath,
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
    fun `1 - When write the attributes mock from ktAsJson and jsonAsKt, Then attributes mock == jsonAsKt and isNotEmpty`() {
//        Given
        val expectedAttributesMock = AttributesMock(attributes = mapOf("a" to 1))
        val fileName = "/generated/request/attributes/notEmpty.json"

//        When
        JsonApi.writeKtAsJsonToFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = fileName,
                value = expectedAttributesMock
        )
        val actualAttributesMock = writeJsonAsKtFromFile(
                relPath = fileName,
                valueType = AttributesMock::class.java
        )

//        Then
        Assertions.assertEquals(expectedAttributesMock, actualAttributesMock)
        assert(expectedAttributesMock.attributes.isNotEmpty())
    }

    @Test
    fun `2 - When write relationshipFake without a dataFake from ktAsJson and jsonAsKt, Then relationshipFake == jsonAsKt and anyRelationship isEmpty`() {
//       Given
        val expectedRelationshipFakeWithoutDataFake = provideRelationshipFake()
        val fileName = "/generated/request/relationship/fakeWithoutDataFake.json"

//        When
        writeKtAsJsonToFile(
                relPath = fileName,
                value = expectedRelationshipFakeWithoutDataFake
        )
        val actualRelationshipFakeWithDataFake = writeJsonAsKtFromFile(
                relPath = fileName,
                valueType = RelationshipFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipFakeWithoutDataFake, actualRelationshipFakeWithDataFake)
        assert(actualRelationshipFakeWithDataFake.anyRelationship.isEmpty())
    }

    @Test
    fun `2B - When write relationshipFake with a dataFake from ktAsJson and jsonAsKt, Then the relationshipFake == jsonAsKt and anyRelationship isNotEmpty`() {
//       Given
        val expectedRelationshipFakeWithDataFake = provideRelationshipFake(
                mapOf("profile" to RelationshipDataFake())
        )
        val fileName = "/generated/request/relationship/fakeWithDataFake.json"

//        When
        writeKtAsJsonToFile(
                relPath = fileName,
                value = expectedRelationshipFakeWithDataFake
        )
        val actualRelationshipFakeWithDataFake = writeJsonAsKtFromFile(
                relPath = fileName,
                valueType = RelationshipFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipFakeWithDataFake, actualRelationshipFakeWithDataFake)
        assert(actualRelationshipFakeWithDataFake.anyRelationship.isNotEmpty())
    }

    @Test
    fun `3A - When write relationshipsFake without relationships from ktAsJson and jsonToKt, Then relationshipsFakeWithoutRelationships == jsonToKt and relationships isEmpty`() {
//        Given
        val expectedRelationshipsFakeWithoutRelationships = provideRelationshipsFake()
        val fileName = "/generated/request/relationships/fakeWithoutRelationships.json"

//        When
        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPath = fileName,
                    value = expectedRelationshipsFakeWithoutRelationships
            )
        }
        val actualRelationshipsWithoutDataFake = writeJsonAsKtFromFile(
                relPath = fileName,
                valueType = RelationshipsFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipsFakeWithoutRelationships, actualRelationshipsWithoutDataFake)
        assert(actualRelationshipsWithoutDataFake.relationships.isEmpty())
    }

    @Test
    fun `3B - When write relationshipsFake with each empty relationship from ktAsJson and jsonAsKt, Then the relationshipsFakeWithEachEmptyRelationship == jsonAsKt and each relationship isEmpty`() {
//        Given
        val expectedRelationshipsWithEachEmptyRelationship = provideRelationshipsFake(
            relationships = listOf(
                    mapOf(),
                    mapOf(),
            )
        )
        val relPath = "/generated/request/relationships/fakeWithEachEmptyRelationship.json"

//        When
        writeKtAsJsonToFile(
            relPath = relPath,
            value = expectedRelationshipsWithEachEmptyRelationship
        )
        val actualRelationshipsWithEachEmptyContent = writeJsonAsKtFromFile(
                relPath = relPath,
                valueType = RelationshipsFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipsWithEachEmptyRelationship, actualRelationshipsWithEachEmptyContent)
        actualRelationshipsWithEachEmptyContent.relationships.map {
            assert(it.isEmpty())
        }
    }

    @Test
    fun `3C - When write relationshipsFake with custom names of relationships from ktAsJson and jsonAsKt, Then relationshipsFake == jsonAsKt and any custom name exists in relationships`() {
//        Given
        val customNameOfProfile = "profile"
        val expectedWithoutGenerics = RelationshipsFake(

                relationships = listOf(
                        mapOf(customNameOfProfile to RelationshipDataFake()),
                        mapOf("settings" to RelationshipDataFake()),
                        mapOf("other" to RelationshipDataFake()),
                )
        )
        val relPath = "/generated/request/relationships/fakeWithoutGenerics.json"

//        When
        writeKtAsJsonToFile(
                relPath = relPath,
                value = expectedWithoutGenerics
        )
        val actualWithoutGenerics = writeJsonAsKtFromFile(
                relPath = relPath,
                valueType = RelationshipsFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedWithoutGenerics, actualWithoutGenerics)
        assert(actualWithoutGenerics.relationships.any {
            it.containsKey(customNameOfProfile)
        })
    }

    @Test
    fun `3D - When write relationshipsFake with each relationship from ktAsJson and jsonAsKt, Then relationshipsFakeWithEachNotEmptyRelationship == jsonAsKt and each relationship isNotEmpty`() {
//        Given
        val expectedRelationshipsFakeWithEachNotEmptyRelationship = RelationshipsFake(
                relationships = listOf(
                        mapOf("profile" to RelationshipDataFake()),
                        mapOf("settings" to RelationshipDataFake())
                )
        )
        val relPath = "/generated/request/relationships/fakeWithEachNotEmptyContent.json"

//        When
        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPath = relPath,
                    value = expectedRelationshipsFakeWithEachNotEmptyRelationship
            )
        }
        val actualRelationshipsWithEachNotEmptyContent = writeJsonAsKtFromFile(
                relPath = relPath,
                valueType = RelationshipsFake::class.java
        )

//        Then
        Assertions.assertEquals(expectedRelationshipsFakeWithEachNotEmptyRelationship, actualRelationshipsWithEachNotEmptyContent)
        actualRelationshipsWithEachNotEmptyContent.relationships.map {
            assert(it.isNotEmpty())
        }
    }

    @Test
    fun `4 - When write Links from ktAsJson and jsonAsKt, Then links == jsonAsKt and the properties are nulls`() {
//        Given
        val expectedLinks = Links()
        val relPath = "/generated/request/links/propertiesNulls.json"

//        When
        writeKtAsJsonToFile(
                relPath = relPath,
                value = expectedLinks
        )
        val actualLinks = writeJsonAsKtFromFile(
                relPath = relPath,
                valueType = Links::class.java
        )

//        Then
        Assertions.assertEquals(expectedLinks, actualLinks)
        assert(actualLinks.self.isNullOrEmpty())
        assert(actualLinks.next.isNullOrEmpty())
        assert(actualLinks.last.isNullOrEmpty())
        assert(actualLinks.related.isNullOrEmpty())
    }

    @Test
    fun `5 - When write a requestData without relationships from ktAsJson and jsonAsKt, Then requestData == jsonAsKt and relationships isEmpty `() {
//       Given
        val expectedRequestDataWithoutRelationships = provideRequestData()
        val relPath = "/generated/request/data/dataWithoutRelationships.json"

//        When
        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPath = relPath,
                    value = expectedRequestDataWithoutRelationships
            )
        }
        val actualRequestDataWithoutRelationships = writeJsonAsKtFromFile(
                relPath = relPath,
                valueType = RequestData::class.java
        )

    //        Then
        Assertions.assertEquals(expectedRequestDataWithoutRelationships, actualRequestDataWithoutRelationships)
        assert(actualRequestDataWithoutRelationships.relationships.isEmpty())
    }

    @Test
    fun `6 - When write a requestData with relationships from ktAsJson and jsonAsKt, Then requestData == jsonAsKt and relationships isNotEmpty`() {
//        Given
        val expectedDataWithRelationships = provideRequestData(
                relationships = listOf(
                        mapOf("profile" to provideRelationshipData()),
                        mapOf("settings" to provideRelationshipData()),
                )
        )
        val relPath = "/generated/request/data/dataWithRelationships.json"

        assertDoesNotThrow {
            writeKtAsJsonToFile(
                    relPath = relPath,
                    value = expectedDataWithRelationships
            )
        }
        val actualData = writeJsonAsKtFromFile(
                relPath = relPath,
                valueType = RequestData::class.java
        )

    //        Then
        Assertions.assertEquals(expectedDataWithRelationships, actualData)
        assert(actualData.relationships.isNotEmpty())
    }

    @Test
    fun `7 - When write a jsonApiRequest without data from ktAsJson and jsonAsKt, Then jsonApiRequest == jsonAsKt and data isEmpty`() {
//        Given
        val expectedJsonApiRequest = provideJsonApiRequest()
        val relPath = "/generated/request/jsonApiRequest/emptyData.json"

//        When
        writeKtAsJsonToFile(
                relPath = relPath,
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPath = relPath,
                valueType = JsonApiRequest::class.java
        )

//        Then
        Assertions.assertEquals(expectedJsonApiRequest, actualJsonApiRequest)
        assert(actualJsonApiRequest.data.isEmpty())
    }

    @Test
    fun `8 - When write a jsonApiRequest with a data and without relationships from ktAsJson and jsonAsKt, Then jsonApiRequest == jsonAsKt, data isNotEmpty and hasNot relationships`() {
//        Given
        val expectedJsonApiRequest = provideJsonApiRequest(data = listOf(provideRequestData()))

//        When
        writeKtAsJsonToFile(
                relPath = "/generated/request/jsonApiRequest/withData.json",
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPath = "/generated/request/jsonApiRequest/withData.json",
                valueType = JsonApiRequest::class.java
        )

//        Then
        Assertions.assertEquals(expectedJsonApiRequest, actualJsonApiRequest)
        assert(actualJsonApiRequest.data.isNotEmpty())
        actualJsonApiRequest.data.map {
            assert(it.relationships.isEmpty())
        }
    }

    @Test
    fun `9 - When write a jsonApiRequest with a data and relationships from ktAsJson and jsonAsKt, Then jsonApiRequest == jsonAsKt, data isNotEmpty and relationships isNotEmpty`() {
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
        val relPath = "/generated/request/jsonApiRequest/withDataAndEmptyRelationships.json"


//        When
        writeKtAsJsonToFile(
                relPath = relPath,
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPath = relPath,
                valueType = JsonApiRequest::class.java
        )

//        Then
        Assertions.assertEquals(expectedJsonApiRequest, actualJsonApiRequest)
        assert(actualJsonApiRequest.data.isNotEmpty())
        actualJsonApiRequest.data.map {
            assert(it.relationships.isNotEmpty())
        }
    }

    @Test
    fun `10 - When write a jsonApiRequest with data in any relationship from ktAsJson and jsonAsKt, Then jsonApiRequest == jsonAsKt and all relationship data isNotEmpty`() {
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
                relPath = "/generated/request/jsonApiRequest/withDataAndNotEmptyRelationships.json",
                value = expectedJsonApiRequest
        )
        val actualJsonApiRequest = writeJsonAsKtFromFile(
                relPath = "/generated/request/jsonApiRequest/withDataAndNotEmptyRelationships.json",
                valueType = JsonApiRequest::class.java
        )

//        Then
        Assertions.assertEquals(expectedJsonApiRequest, actualJsonApiRequest)
        assert(actualJsonApiRequest.data.isNotEmpty())

        actualJsonApiRequest.data.map {
            assert(it.relationships.isNotEmpty())

            it.relationships.map { relationship ->
                assert(relationship.isNotEmpty())
            }
        }
    }

}