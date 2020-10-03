package app.mobius.domain.entity.role

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "resource")
data class Resource(
        @Id @GeneratedValue @Column(name = "resource_uuid") val resourceUUID: UUID? = null,
        @Column(name = "name", unique = true) val name: String,
        @Column(name = "location", unique = true) val location: String,
) {
    constructor() : this(name = "", location = "")
}