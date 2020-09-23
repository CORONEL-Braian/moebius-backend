package app.mobius.data.datasource

import app.mobius.data.di.JDBCManager
import app.moebius.domain.entity.role.Subscription
import org.junit.jupiter.api.Test
import java.util.*

class SqlRoleDataSourceTest {

    @Test
    fun `create subscription`() {
        val uuid = UUID.randomUUID()
        val subscription = Subscription(uuid)
//        print("Test subscription $uuid")
        val session = JDBCManager.openSession(annotatedClass = Subscription::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(subscription)
        }
    }

}