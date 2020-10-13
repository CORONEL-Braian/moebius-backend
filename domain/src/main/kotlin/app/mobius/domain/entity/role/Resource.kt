package app.mobius.domain.entity.role

import java.util.*
import javax.persistence.*


@Entity
@Table(name = "resource")
data class Resource(
        @Id @GeneratedValue @Column(name = "resource_uuid") val resourceUUID: UUID? = null,
        /*@Column(name = "name_test", unique = true)*/val nameTest: String,
        @Column(name = "location", unique = true) val location: String,
) {
    constructor() : this(nameTest = "", location = "")
}