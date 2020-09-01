package app.moebius.domain.object_values.role

import java.util.*

data class Subscription(
        val subscriptionUUID: UUID,
        val name: StatusSubscription = StatusSubscription.FREE
)

enum class StatusSubscription {
    FREE, GOLD, PREMIUM
}