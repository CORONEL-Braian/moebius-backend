package app.mobius.domain.entity.role

import app.mobius.domain.entity.LivenessStatus
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

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum")
        val livenessStatus: LivenessStatus = LivenessStatus.UNSOLICITED,

        @Column(name = "security_level") val securityLevel: Byte = 0,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "subscription_uuid", referencedColumnName = "subscription_uuid")
        val subscription: Subscription,

        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                name = "role_permission",
                joinColumns = [ JoinColumn(name = "role_uuid") ],
                inverseJoinColumns = [ JoinColumn(name = "permission_uuid") ]
        )
        val permissions: List<Permission>

) {
        constructor() : this(subscription = Subscription(), permissions = listOf())
}