package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "subscription")
data class Subscription(
        val subscriptionUUID: UUID,
        val name: StatusSubscription = StatusSubscription.FREE
)

enum class StatusSubscription {
    FREE, GOLD, PREMIUM
}