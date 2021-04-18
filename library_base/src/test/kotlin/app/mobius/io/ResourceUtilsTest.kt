package app.mobius.io

import app.mobius.io.ResourceUtils.getFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
class ResourceUtilsTest {

    @Test
    fun `create or update a file`() {
        val relPath = "/some2.json"

        val absolutePathCurrentModule = System.getProperty("user.dir")
        val pathFromProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }

        val actual = getFile(moduleName = "json-api", parentPath = ParentPath.Test.RESOURCES, relPath = relPath)
        val expected = File("${pathFromProjectRoot}json-api/src${ParentPath.Test.RESOURCES}${relPath}")

        Assertions.assertEquals(actual, expected)
    }

}