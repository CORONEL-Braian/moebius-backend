package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.Resource
import org.hibernate.AssertionFailure
import org.hibernate.HibernateException
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper
import org.hibernate.exception.ConstraintViolationException
import org.junit.jupiter.api.*
import org.postgresql.util.PSQLException
import java.lang.Exception
import kotlin.properties.Delegates

class SqlResourceDataSourceTest {

    private var exists by Delegates.notNull<Boolean>()

    @BeforeAll
    fun before() {
        exists = resourceExist()

    }

    private fun resourceExist(): Boolean {
        return false
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