package app.mobius.data.datasource.role

import app.mobius.data.dataAccess.JDBMConfig
import app.mobius.domain.entity.role.SubscriptionStatus
import app.mobius.domain.entity.role.Subscription
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.io.path.ExperimentalPathApi

@ExperimentalPathApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlSubscriptionDataSourceTest {

    @Test
    fun `create a default subscription`() {
        val subscription = Subscription()
        val session = JDBMConfig.Hibernate.openSession()
        JDBMConfig.Hibernate.executeQuery(session) {
            session.save(subscription)
        }
    }

    @Test
    fun `create a premium subscription`() {
        val subscription = Subscription(subscriptionStatus = SubscriptionStatus.PREMIUM)
        val session = JDBMConfig.Hibernate.openSession()
        JDBMConfig.Hibernate.executeQuery(session) {
            session.save(subscription)
        }
    }

}