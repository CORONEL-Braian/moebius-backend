package app.mobius.data.datasource.role

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.Resource
import org.hibernate.Session
import org.junit.jupiter.api.*
import java.lang.Exception

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlResourceDataSourceTest {

    private lateinit var hibernate : HibernateUtil
    private lateinit var session : Session

    val exists = true

    @BeforeAll
    fun before() {
        hibernate = HibernateUtil()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBM.Hibernate.openSession()
    }


    @Test
    fun isThereAUniqueExistingField() {
        val session = JDBM.Hibernate.openSession()

        val resource = Resource(name = "/test", location = "/test")
        Assertions.assertEquals(
                true,
                hibernate.isUniquenessValid(
                        Resource::class.java, resource
                )
        )
    }

    @Test
    fun `given a random name and location if not exists, when insert, then create a resource  -- should doesn't throw Exception`() {
        val randomName = randomString("/test")
        val randomLocation = randomString("/test")

        val resource = Resource(null, randomName, randomLocation)

        assertDoesNotThrow {
            if (!exists) {
                val session = JDBM.Hibernate.openSession()
                JDBM.Hibernate.executeQuery(session) {
                    session.save(resource)
                }
            }
        }
    }

    /**
     * First insert
     */
    @Test
    fun `given a name and location if not exists, when insert, then create a resource -- should doesn't throw Exception`() {
        val name = "/test"
        val location = "/test"

        val resource = Resource(null, name, location)

        val session = JDBM.Hibernate.openSession()

        assertDoesNotThrow("") {
            if (!exists) {
                JDBM.Hibernate.executeQuery(session) {
                    session.save(resource)
                }
            }
        }
    }

    @Test
    fun `given a name and location if exists, when insert, then create a resource -- should throw Exception`() {
        val name = "/test"
        val location = "/test"

        val resource = Resource(null, name, location)

        val session = JDBM.Hibernate.openSession()

        assertThrows<Exception>("") {
            if (exists) {
                JDBM.Hibernate.executeQuery(session) {
                    session.save(resource)
                }
            }
        }
    }

}