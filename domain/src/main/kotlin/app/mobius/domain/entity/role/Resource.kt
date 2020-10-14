package app.mobius.domain.entity.role

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "resource")
data class Resource(
        @Id @GeneratedValue @Column(name = "resource_uuid") val resourceUUID: UUID? = null,
        @Column(unique = true) val name: String,
        @Column(unique = true) val location: String,
) {
    constructor() : this(name = "", location = "")
}