package app.mobius.domain.mapper.role

import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

/*@Entity
@Table(name = "")
data class Permissions(

)*/

@Entity
@Table(name = "permission")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Permission(
        @Id @GeneratedValue @Column(name = "permission_uuid") val permissionUUID: UUID,

        @Enumerated(EnumType.STRING) @Column(name = "operation") @Type(type = "pgsql_enum")
        val operation: Operation,

        @OneToOne(targetEntity = Resource::class)
        @JoinColumn(name = "resource_uuid", referencedColumnName = "resource_uuid")
        var resource: Resource
)