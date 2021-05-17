package app.mobius.data.datasource.role

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.Resource
import org.hibernate.Session
import org.junit.jupiter.api.*
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlResourceDataSourceTest {

    private lateinit var hibernate : HibernateData
    private lateinit var session : Session

    @BeforeAll
    fun before() {
        hibernate = HibernateData()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBMConfig.Hibernate.openSession()
    }

    @Test
    fun `given a name and location if is uniqueness valid, when insert, then create a resource`() {
        val name = "/test"
        val location = "/test"

        val resource = Resource(null, name, location)

        assertDoesNotThrow("") {
            if (hibernate.isUniquenessValid(resource)) {
                JDBMConfig.Hibernate.executeQuery(session) {
                    session.save(resource)
                }
            }
        }

        Assertions.assertEquals(false, hibernate.isUniquenessValid(resource))
    }

    @Test
    fun `given a random name and location if is uniqueness valid, when insert, then create a resource `() {
        val randomName = randomString("/test")
        val randomLocation = randomString("/test")

        val resource = Resource(null, randomName, randomLocation)

        assertDoesNotThrow {
            if (hibernate.isUniquenessValid(resource)) {
                val session = JDBMConfig.Hibernate.openSession()
                JDBMConfig.Hibernate.executeQuery(session) {
                    session.save(resource)
                }
            }
        }

        Assertions.assertEquals(false, hibernate.isUniquenessValid(resource))
    }


}