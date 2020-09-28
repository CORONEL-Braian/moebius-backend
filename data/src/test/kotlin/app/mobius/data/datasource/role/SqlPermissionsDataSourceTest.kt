package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.Operation
import app.mobius.domain.entity.role.Permission
import app.mobius.domain.entity.role.Resource
import org.junit.jupiter.api.Test

class SqlPermissionsDataSourceTest {

    @Test
    fun `create a permission with nulls in the entities uuid`() {
        val randomLocation = randomString("/test")
        val resource = Resource(resourceUUID = null, name = "test", location = randomLocation)

        val permission = Permission(permissionUUID = null, operation = Operation.CREATE, resource = resource)
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
            session.save(permission)
            val query = session.createQuery("FROM Resource").resultList
            print("Query: $query")
        }
    }

}