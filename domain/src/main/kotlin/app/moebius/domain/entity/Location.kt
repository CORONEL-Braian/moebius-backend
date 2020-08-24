package app.moebius.domain.entity

data class Location(
        val locationId: Int,
        val coordinate: Coordinate,
        val country: String,
        val city: String,
)