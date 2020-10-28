package app.mobius.data.datasource.role

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.Operation
import app.mobius.domain.entity.role.Permission
import app.mobius.domain.entity.role.Resource
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlPermissionsDataSourceTest {

    private lateinit var hibernate : HibernateUtil
    private lateinit var session : Session

    @BeforeAll
    fun before() {
        hibernate = HibernateUtil()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBM.Hibernate.openSession()
    }

    @Test
    fun `given a same permission, when uniqueness is valid and insert, then create a permission`() {
//        Given
        val name = "/test 4"; val location = "/test 4"
        val resource = Resource(null, name, location)
        val permission = Permission(permissionUuid = null, operation = Operation.CREATE, resource = resource)

        assertDoesNotThrow("") {
            if (hibernate.isUniquenessValid(permission)) {
                JDBM.Hibernate.executeQuery(session) {
                    session.save(permission)
                }
            }
        }

        Assertions.assertEquals(false, hibernate.isUniquenessValid(permission))
    }

    @Test
    fun `create a random permission, when uniqueness is valid and insert, then create a permission`() {
//        Given
        val randomName = randomString("/test")
        val randomLocation = randomString("/test")
        val resource = Resource(resourceUUID = null, name = randomName, location = randomLocation)
        val permission = Permission(permissionUuid = null, operation = Operation.CREATE, resource = resource)

//        When
        assertDoesNotThrow("") {
            if (hibernate.isUniquenessValid(permission)) {
                JDBM.Hibernate.executeQuery(session) {
                    session.save(permission)
                }
            }
        }

        Assertions.assertEquals(false, hibernate.isUniquenessValid(permission))
    }

}