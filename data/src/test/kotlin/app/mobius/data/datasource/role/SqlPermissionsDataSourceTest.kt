package app.mobius.data.datasource.role

import app.mobius.data.di.JDBCManager
import app.mobius.data.util.getUUID
import app.mobius.data.util.randomString
import app.mobius.domain.mapper.role.Operation
import app.mobius.domain.mapper.role.Permission
import app.mobius.domain.mapper.role.Resource
import org.junit.jupiter.api.Test
import java.util.*

class SqlPermissionsDataSourceTest {


    @Test
    fun `create a permission`() {
        val resource = Resource(getUUID("resource"), "test", "/test ${randomString()}")
        val resource2 = Resource(
                UUID.fromString("054fd609-9d2d-5da3-89fb-11cd41a8ded9"),
                "test", "/test ${randomString()}")

//        TODO: An option will be save the resource and use uuid it

        val permission = Permission(getUUID("permission"), Operation.CREATE, resource2)
        val session = JDBCManager.openSession_2(annotatedClass = Permission::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(permission)
        }
    }



}