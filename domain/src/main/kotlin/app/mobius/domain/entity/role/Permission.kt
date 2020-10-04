package app.mobius.domain.entity.role

import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

//data class Permissions()

/**
 * https://stackoverflow.com/a/14286082/5279996
 */
@Entity
@Table(
        name = "permission",
       /* uniqueConstraints = [
                UniqueConstraint(columnNames = ["permission_uuid"]),
        ]*/
)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Permission(
        @Id
        @GeneratedValue
        @Column(name = "permission_uuid")
        val permissionUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Column(name = "operation") @Type(type = "pgsql_enum")
        val operation: Operation?,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "resource_uuid", referencedColumnName = "resource_uuid", unique = false)
        var resource: Resource?
) {
        constructor() : this(operation = null, resource = null)
}