package app.mobius.domain.entity.role

import app.mobius.util.PostgreSQLEnumType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import java.util.*
import javax.persistence.*

/**
 * https://stackoverflow.com/a/14286082/5279996
 */
@Entity
@Table(
        name = "permission",
       /* uniqueConstraints = [
                UniqueConstraint(columnNames = ["permissionUUID"]),
        ]*/
)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType::class)
data class Permission(
        @Id
        @GeneratedValue
        @Column(name = "permissionUUID")
        val permissionUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Column(name = "operation") @Type(type = "pgsql_enum")
        val operation: Operation?,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "resourceUUID", referencedColumnName = "resourceUUID", unique = true)
        var resource: Resource?
) {
        constructor() : this(operation = null, resource = null)
}