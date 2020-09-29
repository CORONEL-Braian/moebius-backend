package app.mobius.domain.entity.role

import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*
import javax.persistence.Entity;

@Entity
@Table(name = "subscription")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Subscription(
        @Id @GeneratedValue @Column(name = "subscription_uuid", updatable = false)
        val subscriptionUUID: UUID? = null,
        @Enumerated(EnumType.STRING) @Column(name = "status_subscription") @Type(type = "pgsql_enum")
        val statusSubscription: StatusSubscription = StatusSubscription.FREE
) {
    constructor() : this(null)
}

enum class StatusSubscription {
    FREE, PREMIUM
}