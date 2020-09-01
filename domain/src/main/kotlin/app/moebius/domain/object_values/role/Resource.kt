package app.moebius.domain.object_values.role

import java.util.*

data class Resource(
        val resourceUUID: UUID,
        val name: String,
        val location: String,
)