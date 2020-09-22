package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "subscription")
data class Subscription(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val subscriptionUUID: UUID,
        val name: StatusSubscription = StatusSubscription.FREE
)

enum class StatusSubscription {
    FREE, GOLD, PREMIUM
}