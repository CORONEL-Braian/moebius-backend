package app.mobius.domain.entity.role

import app.mobius.domain.entity.LivenessStatus
import app.mobius.util.PostgreSQLEnumType
import com.fasterxml.jackson.annotation.JsonIgnore
import io.crnk.core.resource.annotations.JsonApiRelation
import io.crnk.core.resource.annotations.JsonApiRelationId
import io.crnk.core.resource.annotations.JsonApiResource
import io.crnk.core.resource.annotations.SerializeType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

@JsonApiResource(type = "role")
@Entity
@Table(name = "role")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Role(
//        @JsonApiRelationId
        @Id @GeneratedValue @Column(name = "role_uuid") val roleUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum")
        val livenessStatus: LivenessStatus = LivenessStatus.UNSOLICITED,

        @Column(name = "security_level") val securityLevel: Byte = 0,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "subscription_uuid", referencedColumnName = "subscription_uuid")
        val subscription: Subscription,

        @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinTable(
                name = "role_permission",
                joinColumns = [ JoinColumn(name = "role_uuid") ],
                inverseJoinColumns = [ JoinColumn(name = "permission_uuid") ]
        )
//        @JsonApiRelation(serialize = SerializeType.ONLY_ID)
        @JsonIgnore
        val permissions: List<Permission>

) {
        constructor() : this(subscription = Subscription(), permissions = listOf())
}