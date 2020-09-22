package app.mobius.data.datasource

import app.mobius.data.di.JDBCManager
import app.moebius.domain.entity.role.Subscription
import io.mockk.mockk
import org.junit.jupiter.api.Test

class SqlRoleDataSourceTest {

    @Test
    fun `create subscription`() {
        val subscription = mockk<Subscription>()
        val session = JDBCManager.openSession(annotatedClass = Subscription::class.java)
        JDBCManager.executeQuery(session, "Work") {
            session.save(subscription)
        }
    }

}