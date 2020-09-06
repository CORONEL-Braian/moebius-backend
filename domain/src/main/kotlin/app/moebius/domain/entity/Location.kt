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

data class CurrentLocation(
        val currentLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City,
        val direction: Direction? = null
)

data class Country(
        val countryUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

data class Province(
        val provinceUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

data class City(
        val cityUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

data class LocationLimits(
        val limitsUUID: UUID,
        val northeast: Coordinate,
        val southwest: Coordinate
)

data class Direction(
        val directionUUID: UUID,
        val street: String,
        val number: Int,
        val firstBetweenStreet: String,
        val secondBetweenStreet: String,
        val coordinate: Coordinate
)

data class Coordinate(
        val lat: Float,
        val lng: Float,
)