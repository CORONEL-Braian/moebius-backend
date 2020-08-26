package app.moebius.domain.model.rol

import java.util.*

data class Resource(
        val resourceUUID: UUID,
        val name: String,
        val location: String,
)