package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "resource")
data class Resource(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "resource_uuid") val resourceUUID: UUID,
        val name: String,
        val location: String,
)