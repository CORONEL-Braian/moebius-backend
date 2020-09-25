package app.mobius.data.datasource.role

import app.mobius.data.di.JDBCManager
import app.mobius.data.util.getUUID
import app.mobius.data.util.randomString
import app.moebius.domain.entity.role.Resource
import org.junit.jupiter.api.Test

class SqlResourceDataSourceTest {

    @Test
    fun `create a resource`() {
        val resource = Resource(getUUID("resource"), "test", "/test ${randomString()}")

        val session = JDBCManager.openSession(annotatedClass = Resource::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(resource)
        }
    }

}