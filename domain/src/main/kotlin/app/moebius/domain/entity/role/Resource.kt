package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.*

data class Resource(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val resourceUUID: UUID,
        val name: String,
        val location: String,
)