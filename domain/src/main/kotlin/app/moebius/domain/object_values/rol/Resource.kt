package app.moebius.domain.object_values.rol

import java.util.*

data class Resource(
        val resourceUUID: UUID,
        val name: String,
        val location: String,
)