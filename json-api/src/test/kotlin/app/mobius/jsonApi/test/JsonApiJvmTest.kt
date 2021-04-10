package app.mobius.jsonApi.test

import app.mobius.io.ParentPathFile
import app.mobius.jsonApi.JsonApi
import app.mobius.jsonApi.test.model.jvm.SomeList
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class JsonApiJvmTest {

    @Test
    fun `1 - When write a some list from json to kt, then shouldn't throw an exception`() {
        assertDoesNotThrow {
            JsonApi.writeJsonAsKtFromFile(
                    moduleName = JsonApi.MODULE_NAME_JSON_API,
                    parentPathFile = ParentPathFile.Test.RESOURCES,
                    relPathFile = "/generated/jvm/someList.json",
                    valueType = SomeList::class.java
            )
        }
    }

}