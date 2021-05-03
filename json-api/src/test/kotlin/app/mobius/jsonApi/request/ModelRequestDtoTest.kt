package app.mobius.jsonApi.request

import app.mobius.io.ParentPath
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.model.jvm.SomeList
import app.mobius.jsonApi.model.jvm.SomeListWithoutConstructor
import app.mobius.jsonApi.model.request.JsonApiMapper
import app.mobius.jsonApi.model.request.JsonApiRequest
import app.mobius.jsonApi.model.request.SampleRequestDto
import org.junit.jupiter.api.Test
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
class ModelRequestDtoTest {

    @Test
    fun `1 - When write jsonApi as kt from file a sample, then data isEmpty`() {
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/emptyData.json",
                valueType = JsonApiRequest::class.java
        )

        assert(sampleJsonApi.data.isEmpty())
    }

    @Test
    fun `2 - When write jsonApi as kt from file a sample, then type isNotEmpty`() {
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/withType.json",
                valueType = JsonApiRequest::class.java
        )

        assert(sampleJsonApi.data.first().type.isNotEmpty())
    }

    @Test
    fun `3 - When write jsonApi as kt from file a sample, then attributes isNotEmpty`() {
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/attributes/withTitleAndSrc.json",
                valueType = JsonApiRequest::class.java
        )

        assert(sampleJsonApi.data.first().attributes.isNotEmpty())
    }

    @Test
    fun `4 - When write jsonApi as kt from file a sample, then relationships isEmpty`() {
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/withEmptyRelationships.json",
                valueType = JsonApiRequest::class.java
        )

        assert(sampleJsonApi.data.first().relationships.isEmpty())
    }

    @Test
    fun `5 - When write a sample of jsonApi as kt from file, then relationships isNotEmpty`() {
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/relationships/withEmptyPhotographer.json",
                valueType = JsonApiRequest::class.java
        )

        assert(sampleJsonApi.data.first().relationships.isNotEmpty())
    }

    @Test
    fun `6A - When map a empty JsonApiRequest without default constructor, Then any class instance is returned`() {
        JsonApiMapper.mapJsonApiToDtoRequest(JsonApiRequest(), SomeListWithoutConstructor::class.java)
    }

    @Test
    fun `6B - When map a empty JsonApiRequest, Then any class instance is returned and map isEmpty`() {
        val someList = SomeList()
        JsonApiMapper.mapJsonApiToDtoRequest(JsonApiRequest(), SomeList::class.java)

        assert(someList.map.isEmpty())
    }

    @Test
    fun `7 - When map a JsonApiRequest with emptyData , Then a instance of SampleRequestDTO is returned`() {
        JsonApiMapper.mapJsonApiToDtoRequest(JsonApiRequest(), SampleRequestDto::class.java)
    }

    @Test
    fun `8 - When map a JsonApiRequest with title as attribute from file, Then a instance of SampleRequestDTO has not a empty title`() {
//        Given
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/attributes/withTitle.json",
                valueType = JsonApiRequest::class.java
        )

//        When
        val sampleRequestDto = JsonApiMapper.mapJsonApiToDtoRequest(sampleJsonApi, SampleRequestDto::class.java)

//        Then
        assert(sampleRequestDto.title.isNotEmpty())
    }

    @Test
    fun `9 - When map a JsonApiRequest with title and src as attribute from file, Then a instance of SampleRequestDTO has not a empty title nor empty src`() {
//        Given
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/attributes/withTitleAndSrc.json",
                valueType = JsonApiRequest::class.java
        )

//        When
        val sampleRequestDto = JsonApiMapper.mapJsonApiToDtoRequest(sampleJsonApi, SampleRequestDto::class.java)

//        Then
        assert(sampleRequestDto.title.isNotEmpty())
        assert(sampleRequestDto.src.isNotEmpty())
    }

    @Test
    fun `10 - When map a JsonApiRequest with relationships from file, Then a instance of SampleRequestDTO has a photographer with type and id in relationships`() {
//        Given
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/relationships/withPhotographer.json",
                valueType = JsonApiRequest::class.java
        )

//        When
        val sampleRequestDto = JsonApiMapper.mapJsonApiToDtoRequest(sampleJsonApi, SampleRequestDto::class.java)

//        Then
        assert(sampleRequestDto.photographer.type.isNotEmpty())
        assert(sampleRequestDto.photographer.id.isNotEmpty())
    }

    @Test
    fun `X - When map a full JsonApiRequest, Then an instance of SampleRequestDTO is returned`() {

    }

}