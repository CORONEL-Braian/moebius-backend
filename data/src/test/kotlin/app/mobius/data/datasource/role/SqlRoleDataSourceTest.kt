package app.mobius.data.datasource.role

import app.mobius.data.di.JDBCManager
import app.moebius.domain.entity.role.StatusSubscription
import app.moebius.domain.entity.role.Subscription
import org.junit.jupiter.api.Test
import java.util.*

class SqlRoleDataSourceTest {

    @Test
    fun `create a default subscription`() {
        val uuid = UUID.randomUUID()
        val subscription = Subscription(uuid)
        print("Test subscription $uuid")
        val session = JDBCManager.openSession(annotatedClass = Subscription::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(subscription)
        }
    }

    @Test
    fun `create a premium subscription`() {
        val uuid = UUID.randomUUID()
        val subscription = Subscription(uuid, StatusSubscription.PREMIUM)
        print("Test subscription $uuid")
        val session = JDBCManager.openSession(annotatedClass = Subscription::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(subscription)
        }
    }

}