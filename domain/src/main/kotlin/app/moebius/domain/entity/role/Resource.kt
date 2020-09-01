package app.moebius.domain.entity.role

import java.util.*

data class Resource(
        val resourceUUID: UUID,
        val name: String,
        val location: String,
)