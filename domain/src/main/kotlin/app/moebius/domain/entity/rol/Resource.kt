package app.moebius.domain.entity.rol

import java.util.*

data class Resource(
        val resourceUUID: UUID,
        val name: String,
        val location: String,
)