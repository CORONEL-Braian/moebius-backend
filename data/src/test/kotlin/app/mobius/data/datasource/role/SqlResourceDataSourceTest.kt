package app.mobius.data.datasource.role

import app.mobius.data.di.JDBCManager
import app.mobius.data.util.getUUID
import app.mobius.data.util.randomString
import app.mobius.domain.mapper.role.Resource
import org.junit.jupiter.api.Test

class SqlResourceDataSourceTest {

    @Test
    fun `create a resource`() {
        val resource = Resource(getUUID("resource"), "test", "/test ${randomString()}")

        val session = JDBCManager.openSession_2(annotatedClass = Resource::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(resource)
        }
    }

}