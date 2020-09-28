package app.mobius.domain.entity.role

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "resource")
data class Resource(
        @Id @GeneratedValue @Column(name = "resource_uuid") val resourceUUID: UUID,
        @Column(name = "name") val name: String,
        @Column(name = "location") val location: String,

        /*@OneToOne(mappedBy = "resource", cascade = [CascadeType.ALL])
        var permission: Permission? = null*/
) {
    constructor() : this(UUID.randomUUID(), "", "")
}