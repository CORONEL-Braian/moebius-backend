package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.*
import org.junit.jupiter.api.Test

class SqlRoleDataSourceTest {

    @Test
    fun `create a role without permissions`() {
        val role = Role()

        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(role)
        }
    }

    @Test
    fun `create a role with permissions`() {
//        Given
        val randomLocation = randomString("/test")
        val resource = Resource(resourceUUID = null, name = "test", location = randomLocation)
        val permission = Permission(permissionUUID = null, operation = Operation.CREATE, resource = resource)

//        When
        val permissions = listOf(
                permission
        )
        val role = Role(subscription = Subscription(), permissions = permissions)
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(role)
        }
    }


}