package app.mobius.io

import app.mobius.io.ResourceUtils.getFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class ResourceUtilsTest {

    @Test
    fun `create or update a file`() {
        val relPathFile = "/some2.json"

        val absolutePathCurrentModule = System.getProperty("user.dir")
        val pathFromProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }

        val actual = getFile(moduleName = "json-api", parentPath = ParentPathFile.Test.RESOURCES, relPath = relPathFile)
        val expected = File("${pathFromProjectRoot}json-api/src${ParentPathFile.Test.RESOURCES}${relPathFile}")

        Assertions.assertEquals(actual, expected)
    }

}