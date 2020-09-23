package app.moebius.domain.entity.role

import app.moebius.domain.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "subscription")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Subscription(

        @Id @GeneratedValue @Column(name = "subscription_uuid", updatable = false)
        val subscriptionUUID: UUID,

        @Enumerated(EnumType.STRING) @Column(name = "status_subscription") @Type(type = "pgsql_enum")
        val statusSubscription: StatusSubscription = StatusSubscription.FREE

)

enum class StatusSubscription {
    FREE, GOLD, PREMIUM
}