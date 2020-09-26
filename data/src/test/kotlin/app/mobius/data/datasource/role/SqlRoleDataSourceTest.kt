package app.mobius.data.datasource.role

import app.mobius.data.di.JDBCManager
import app.mobius.domain.mapper.role.StatusSubscription
import app.mobius.domain.mapper.role.Subscription
import org.junit.jupiter.api.Test
import java.util.*

class SqlRoleDataSourceTest {

    @Test
    fun `create a default subscription`() {
        val uuid = UUID.randomUUID()
        val subscription = Subscription(uuid)
        print("Test subscription $uuid")
        val session = JDBCManager.openSession_1_1(Subscription::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(subscription)
        }
    }

    @Test
    fun `create a premium subscription`() {
        val uuid = UUID.randomUUID()
        val subscription = Subscription(uuid, StatusSubscription.PREMIUM)
        print("Test subscription $uuid")
        val session = JDBCManager.openSession_2(annotatedClass = Subscription::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(subscription)
        }
    }

}