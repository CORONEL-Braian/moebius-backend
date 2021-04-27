package app.mobius.jsonApi.request

import app.mobius.io.ParentPath
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.model.jvm.SomeList
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
                relPath = "/request/sample/withRelationships.json",
                valueType = JsonApiRequest::class.java
        )

        assert(sampleJsonApi.data.first().relationships.isNotEmpty())
    }

    @Test
    fun `6 - When map a empty JsonApiRequest, Then any class instance is returned`() {
        JsonApiMapper.mapGenericToDtoRequest(JsonApiRequest(), SomeList::class.java)
    }

    @Test
    fun `7 - When map a JsonApiRequest with emptyData , Then a instance of SampleRequestDTO is returned`() {
        JsonApiMapper.mapGenericToDtoRequest(JsonApiRequest(), SampleRequestDto::class.java)
    }

    @Test
    fun `8 - When map a JsonApiRequest with title as attribute from file, Then a instance of SampleRequestDTO has a not null title`() {
//        Given
        val sampleJsonApi = JsonApi.writeJsonAsKtFromFile(
                moduleName = JsonApi.MODULE_NAME_JSON_API,
                parentPath = ParentPath.Test.RESOURCES,
                relPath = "/request/sample/attributes/withTitle.json",
                valueType = JsonApiRequest::class.java
        )

//        When
        val sampleRequestDto = JsonApiMapper.mapGenericToDtoRequest(JsonApiRequest(), SampleRequestDto::class.java)

//        Then
        assert(sampleRequestDto.title.isNotEmpty())
    }



    @Test
    fun `X - When map a full JsonApiRequest, Then an instance of SampleRequestDTO is returned`() {

    }

}