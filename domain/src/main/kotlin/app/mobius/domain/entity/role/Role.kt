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
        @Id @GeneratedValue @Column(name = "roleUUID") val roleUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Column(name = "livenessStatus") @Type(type = "pgsql_enum")
        val livenessStatus: LivenessStatus = LivenessStatus.UNSOLICITED,

        @Column(name = "securityLevel") val securityLevel: Int = 0,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "subscriptionUUID", referencedColumnName = "subscriptionUUID")
        val subscription: Subscription,

        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(
                name = "role_permission",
                joinColumns = [ JoinColumn(name = "roleUUID") ],
                inverseJoinColumns = [ JoinColumn(name = "permissionUUID") ]
        )
        val permissions: List<Permission>

) {
        constructor() : this(subscription = Subscription(), permissions = listOf())
}