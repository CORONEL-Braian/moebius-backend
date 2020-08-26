package app.moebius.domain.model

import java.util.*

data class Location(
        val locationUUID: UUID,
        val coordinate: Coordinate,
        val country: String,
        val city: String,
)