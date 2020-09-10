package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "location")
data class Location(
        val locationUUID: UUID,
        val originLocation: OriginLocation,
        val currentLocation: CurrentLocation
)

@Entity
@Table(name = "origin_location")
data class OriginLocation(
        val originLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City
)

@Entity
@Table(name = "current_location")
data class CurrentLocation(
        val currentLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City,
        val direction: Direction? = null
)

@Entity
@Table(name = "country")
data class Country(
        val countryUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "province")
data class Province(
        val provinceUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "city")
data class City(
        val cityUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "location_limits")
data class LocationLimits(
        val limitsUUID: UUID,
        val northeast: Coordinate,
        val southwest: Coordinate
)

@Entity
@Table(name = "direction")
data class Direction(
        val directionUUID: UUID,
        val street: String,
        val number: Int,
        val firstBetweenStreet: String,
        val secondBetweenStreet: String,
        val coordinate: Coordinate
)

@Entity
@Table(name = "coordinate")
data class Coordinate(
        val lat: Float,
        val lng: Float,
)