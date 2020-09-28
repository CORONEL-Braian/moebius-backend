package app.mobius.domain.entity.role

import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

/*@Entity
@Table(name = "")
data class Permissions(

)*/

/**
 * https://stackoverflow.com/a/14286082/5279996
 */
@Entity
@Table(name = "permission")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Permission(
       /* @Id
        @GeneratedValue()
        @GenericGenerator(name = "hibernate-uuid", strategy = "uuid")
        @Column(name = "permission_uuid")
        val permissionUUID: UUID?, */

        @Id
        @GeneratedValue(generator = "UUID", strategy = GenerationType.IDENTITY)
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "permission_uuid", updatable = false, nullable = false)
        val uuid: UUID? = null,

        @Enumerated(EnumType.STRING) @Column(name = "operation") @Type(type = "pgsql_enum")
        val operation: Operation?,

        @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
        @JoinColumn(name = "resource_uuid", referencedColumnName = "resource_uuid")
        var resource: Resource?
) {
        constructor() : this(operation = null, resource = null)
}