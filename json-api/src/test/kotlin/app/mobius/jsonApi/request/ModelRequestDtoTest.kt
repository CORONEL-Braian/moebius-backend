package app.mobius.jsonApi.request

import app.mobius.io.ParentPath
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.model.request.JsonApiRequest
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
                relPath = "/request/sample/withAttributes.json",
                valueType = JsonApiRequest::class.java
        )

        assert(sampleJsonApi.data.first().attributes.isNotEmpty())
    }


}