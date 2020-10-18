package app.mobius.data.datasource.role

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.*
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlRoleDataSourceTest {

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
    fun `create a role without permissions`() {
        val role = Role()

        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(role)
        }
    }

    @Test
    fun `given a resource and permissions, when insert role, then create a role -- should does not throw Exception`() {
//        Given
        val randomName = randomString("/test")
        val randomLocation = randomString("/test")
        val resource = Resource(null, randomName, randomLocation)
        val permission1 = Permission(permissionUuid = null, operation = Operation.UPDATE, resource = resource)
        val permission2 = Permission(permissionUuid = null, operation = Operation.UPDATE, resource = resource)


//        When
        val permissions = listOf(
                permission1,
                permission2
        )
        val role = Role(subscription = Subscription(), permissions = permissions)


        assertDoesNotThrow("") {
            if (hibernate.isUniquenessValid(role)) {
                JDBM.Hibernate.executeQuery(session) {
                    session.save(role)
                }
            }
        }

        Assertions.assertEquals(false, hibernate.isUniquenessValid(role))

    }


}