package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "subscription")
data class Subscription(

        @Id @GeneratedValue @Column(name = "subscription_uuid", columnDefinition = "uuid", updatable = false)
        val subscriptionUUID: UUID,

        /*@Enumerated(EnumType.ORDINAL)*/ @Column(name = "status_subscription")
        val statusSubscription: StatusSubscription = StatusSubscription.FREE
)

enum class StatusSubscription {
    FREE, GOLD, PREMIUM
}