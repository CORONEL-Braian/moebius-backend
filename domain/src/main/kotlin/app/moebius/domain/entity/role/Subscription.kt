package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "subscription")
data class Subscription(
        @Id val subscriptionUUID: UUID,
        val name: StatusSubscription = StatusSubscription.FREE
)

enum class StatusSubscription {
    FREE, GOLD, PREMIUM
}