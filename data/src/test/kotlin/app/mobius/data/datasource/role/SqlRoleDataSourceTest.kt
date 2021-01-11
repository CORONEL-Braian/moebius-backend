package app.mobius.data.datasource.role

import app.mobius.data.dataAccess.hibernate.HibernateData
import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.*
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlRoleDataSourceTest {

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
    fun `create a role without permissions`() {
        val role = Role()

        val session = JDBMConfig.Hibernate.openSession()
        JDBMConfig.Hibernate.executeQuery(session) {
            session.save(role)
        }
    }

    @Test
    fun `given a resource and permissions, when insert role, then create a role`() {
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
                JDBMConfig.Hibernate.executeQuery(session) {
                    session.save(role)
                }
            }
        }

        Assertions.assertEquals(false, hibernate.isUniquenessValid(role))

    }


}