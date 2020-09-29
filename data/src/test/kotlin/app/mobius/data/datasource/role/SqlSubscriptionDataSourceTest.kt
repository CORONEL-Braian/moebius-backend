package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.domain.entity.role.StatusSubscription
import app.mobius.domain.entity.role.Subscription
import org.junit.jupiter.api.Test

class SqlSubscriptionDataSourceTest {

    @Test
    fun `create a default subscription`() {
        val subscription = Subscription()
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(subscription)
        }
    }

    @Test
    fun `create a premium subscription`() {
        val subscription = Subscription(statusSubscription = StatusSubscription.PREMIUM)
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(subscription)
        }
    }

}