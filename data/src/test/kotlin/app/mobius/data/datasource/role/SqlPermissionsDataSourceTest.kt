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


    @Test
    fun `create a permission`() {
        val randomLocation = randomString("/test")
        val resource = Resource(name = "test", location = randomLocation)

//        TODO: An option will be save the resource and use uuid it

        val permission = Permission(operation = Operation.CREATE, resource = resource)
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
            session.save(permission)
            val query = session.createQuery("FROM Resource").resultList
            print("Query: $query")
        }
    }


}