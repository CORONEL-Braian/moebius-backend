package app.mobius.data.datasource.role

import app.mobius.data.di.JDBCManager
import app.moebius.domain.entity.role.Operation
import app.moebius.domain.entity.role.Permission
import app.moebius.domain.entity.role.Resource
import org.junit.jupiter.api.Test
import java.util.*

class SqlPermissionsDataSourceTest {

    private fun getUUID(msg: String) = UUID.randomUUID().also { print("New UUID of $msg: $it") }

    @Test
    fun `create a permission`() {
        val resource = Resource(getUUID("resource"), "User", "/user" )
        val permission = Permission(getUUID("permission"), Operation.CREATE, resource)
        val session = JDBCManager.openSession(annotatedClass = Permission::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(resource)
        }
    }



}