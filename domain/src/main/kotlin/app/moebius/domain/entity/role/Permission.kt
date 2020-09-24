package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.*

/*@Entity
@Table(name = "")
data class Permissions(

)*/

@Entity
@Table(name = "permission")
data class Permission(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "permission_uuid") val permissionUUID: UUID,
        val operation: Operation,
        val resource: Resource
)