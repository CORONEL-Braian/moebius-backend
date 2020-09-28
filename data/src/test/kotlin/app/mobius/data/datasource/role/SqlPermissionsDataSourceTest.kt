package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.data.util.getUUID
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.Operation
import app.mobius.domain.entity.role.Permission
import app.mobius.domain.entity.role.Resource
import org.junit.jupiter.api.Test
import java.util.*

class SqlPermissionsDataSourceTest {


//TODO: An option will be save the resource with a date and use it for get uuid
    @Test
    fun `create a permission`() {
        val randomLocation = randomString("/test")
        val resource = Resource(name = "test", location = randomLocation)


        val permission = Permission(operation = Operation.CREATE, resource = resource)
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
            session.save(permission)
            val query = session.createQuery("FROM Resource").resultList
            print("Query: $query")
        }
    }


}