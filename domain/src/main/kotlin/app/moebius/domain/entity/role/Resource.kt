package app.moebius.domain.entity.role

import java.util.*
import javax.persistence.Id

data class Resource(
        @Id val resourceUUID: UUID,
        val name: String,
        val location: String,
)