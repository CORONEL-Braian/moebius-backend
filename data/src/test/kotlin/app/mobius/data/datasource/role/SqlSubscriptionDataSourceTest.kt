package app.mobius.data.datasource.role

import app.mobius.data.di.JDBM
import app.mobius.domain.entity.role.StatusSubscription
import app.mobius.domain.entity.role.Subscription
import org.junit.jupiter.api.Test
import java.util.*

class SqlSubscriptionDataSourceTest {

    @Test
    fun `create a default subscription`() {
        val uuid = UUID.randomUUID()
        val subscription = Subscription(uuid)
        print("Test subscription $uuid")
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
            session.save(subscription)
        }
    }

    @Test
    fun `create a premium subscription`() {
        val uuid = UUID.randomUUID()
        val subscription = Subscription(uuid, StatusSubscription.PREMIUM)
        print("Test subscription $uuid")
        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session, "Work") {
            session.save(subscription)
        }
    }

}