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
    fun testJoin() {

    }

    @Test
    fun `given a permission and is uniqueness valid, when insert, then create a permission -- should doesn't throw Exception`() {
//        Given
        val name = "/test 4"; val location = "/test 4"
        val resource = Resource(null, name, location)
        val permission = Permission(permissionUUID = null, operation = Operation.CREATE, resource = resource)

        assertDoesNotThrow("") {
         /*   if (hibernate.isUniquenessValid(permission)) {
                JDBM.Hibernate.executeQuery(session) {
                    session.save(permission)
                }
            }*/
        }

        Assertions.assertEquals(false, hibernate.isUniquenessValid(permission))
    }

    @Test
    fun `create a permission with nulls in the entities uuid`() {
//        Given
        val randomName = randomString("/test")
        val randomLocation = randomString("/test")
        val resource = Resource(resourceUUID = null, name = randomName, location = randomLocation)
        val permission = Permission(permissionUUID = null, operation = Operation.CREATE, resource = resource)

//        When
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(permission)
            val query = session.createQuery("FROM Resource").resultList
            print("Query: $query")
        }
    }

}