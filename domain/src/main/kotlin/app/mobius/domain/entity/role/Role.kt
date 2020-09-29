package app.mobius.domain.entity.role

import app.mobius.domain.entity.StatusLiveness
import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "role")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Role(
        @Id @GeneratedValue @Column(name = "role_uuid") val roleUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Column(name = "status_liveness") @Type(type = "pgsql_enum")
        val statusLiveness: StatusLiveness = StatusLiveness.UNSOLICITED,

        @Column(name = "security_level") val securityLevel: Int = 0,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "subscription_uuid", referencedColumnName = "subscription_uuid")
        val subscription: Subscription,
//        val permissions: List<Permission>?
) {
        constructor() : this(subscription = Subscription())
}
