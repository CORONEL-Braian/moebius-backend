package app.moebius.domain.model

data class Location(
        val locationUUID: Int,
        val coordinate: Coordinate,
        val country: String,
        val city: String,
)