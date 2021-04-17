package app.mobius.data.di

import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.role.Resource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.FileInputStream
import java.io.InputStream
import kotlin.io.path.ExperimentalPathApi

/**
 * PRECONDITION: Use A or B
 *   A) Build, Execution & Deployment -> Build Tools -> Gradle
 *      . Build and run using: Intellij Idea
 *      . Run tests usging: Intellij Idea
 *
 *   B) useJUnitPlatform()
 */
@ExperimentalPathApi
class JDBMTest {

//      ----- With Hibernate CFG  -----
    @Test
    fun `get input stream for configuration of session factory`() {
        val absolutePathCurrentModule = System.getProperty("user.dir")
        val absolutePathProjectRoot = absolutePathCurrentModule.dropLastWhile { it != '/' }

        val absoulutePathOfFile = "$absolutePathProjectRoot/data-core/src/main/resources/secret-hibernate.cfg.xml"
        val targetStream: InputStream = FileInputStream(absoulutePathOfFile)
        Assertions.assertNotEquals(targetStream, null)
    }

    @Test
    fun `open session with hibernate cfg and only one entity`() {
        JDBMConfig.Hibernate.openSessionForOnly(Resource::class.java)
    }

    @Test
    fun `close session with hibernate cfg and only one entity`() {
        val session = JDBMConfig.Hibernate.openSessionForOnly(Resource::class.java)

//        Execute an empty operation for close session
        JDBMConfig.Hibernate.executeQuery(session, "") {}
    }

    @Test
    fun `open session with hibernate cfg and reflextion for all mapped entities`() {
        JDBMConfig.Hibernate.openSession()
    }

}