package app.mobius.data.datasource.role

import app.mobius.data.di.JDBCManager
import app.mobius.data.util.getUUID
import app.mobius.data.util.randomString
import app.moebius.domain.entity.role.Operation
import app.moebius.domain.entity.role.Permission
import app.moebius.domain.entity.role.Resource
import org.junit.jupiter.api.Test
import java.util.*

class SqlPermissionsDataSourceTest {


    @Test
    fun `create a permission`() {
        val resource = Resource(getUUID("resource"), "test", "/test ${randomString()}")

//        TODO: An option will be save the resource and use uuid it

        val permission = Permission(getUUID("permission"), Operation.CREATE, resource)
        val session = JDBCManager.openSession(annotatedClass = Permission::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(permission)
        }
    }



}