package app.mobius.data.datasource

import app.mobius.data.di.JDBCManager
import app.moebius.domain.entity.role.Subscription2
import org.junit.jupiter.api.Test

class SqlRoleDataSourceTest {

    @Test
    fun `create subscription`() {
//        val uuid = UUID.randomUUID()
//        val subscription = Subscription2(uuid, StatusSubscription.FREE)
//        print("Test subscription $uuid")
        val session = JDBCManager.openSession(annotatedClass = Subscription2::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(Subscription2())
        }
    }

}