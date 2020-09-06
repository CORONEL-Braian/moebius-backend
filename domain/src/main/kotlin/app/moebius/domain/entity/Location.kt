package app.moebius.domain.entity

import java.util.*

data class Location(
        val locationUUID: UUID,
        val originLocation: OriginLocation,
        val currentLocation: CurrentLocation
)

data class OriginLocation(
        val originLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City
)

/**
 * An island would only have one coordinate
 */
data class CurrentLocation(
        val currentLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City,
        val direction: Direction? = null,
        val coordinate: Coordinate? = null
)

data class Country(
        val countryUUID: UUID,
        val name: String,
        val locationsLimits: LocationsLimits,
)

data class Province(
        val provinceUUID: UUID,
        val name: String,
        val locationsLimits: LocationsLimits,
)

data class City(
        val cityUUID: UUID,
        val name: String,
        val locationsLimits: LocationsLimits,
)

data class LocationsLimits(
        val limitsUUID: UUID,
        val northeast: Coordinate,
        val southwest: Coordinate
)

data class Direction(
        val directionUUID: UUID,
        val name: String,
        val number: Int,
        val street_one: String,
        val street_two: String,
        val coordinate: Coordinate
)

data class Coordinate(
        val lat: Float,
        val lng: Float,
)