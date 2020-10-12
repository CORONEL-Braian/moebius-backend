package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "location")
data class Location(
        @Id @GeneratedValue @Column(name = "locationUUID") val locationUUID: UUID? = null,
        val originLocation: OriginLocation,
        val currentLocation: CurrentLocation
)

@Entity
@Table(name = "originLocation")
data class OriginLocation(
        @Id @GeneratedValue @Column(name = "origin_locationUUID") val originLocationUUID: UUID? = null,
        val country: Country,
        val province: Province,
        val city: City
)

@Entity
@Table(name = "currentLocation")
data class CurrentLocation(
        @Id @GeneratedValue @Column(name = "current_locationUUID") val currentLocationUUID: UUID? = null,
        val country: Country,
        val province: Province,
        val city: City,
        val direction: Direction? = null
)

@Entity
@Table(name = "country")
data class Country(
        @Id @GeneratedValue @Column(name = "countryUUID") val countryUUID: UUID? = null,
        val name: String,
        val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

@Entity
@Table(name = "province")
data class Province(
        @Id @GeneratedValue @Column(name = "provinceUUID") val provinceUUID: UUID? = null,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "city")
data class City(
        @Id @GeneratedValue @Column(name = "cityUUID") val cityUUID: UUID? = null,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "locationLimits")
data class LocationLimits(
        @Id @GeneratedValue @Column(name = "location_limitsUUID") val limitsUUID: UUID? = null,
        val northeast: Coordinate,
        val southwest: Coordinate
) {
    constructor() : this(northeast = Coordinate(), southwest = Coordinate())
}

@Entity
@Table(name = "direction")
data class Direction(
        @Id @GeneratedValue @Column(name = "directionUUID") val directionUUID: UUID? = null,
        val street: String,
        val number: Int,
        val firstBetweenStreet: String,
        val secondBetweenStreet: String,
        val coordinate: Coordinate
)

@Entity
@Table(name = "coordinate")
data class Coordinate(
        @Id @GeneratedValue @Column(name = "coordinateUUID") val coordinateUUID: UUID? = null,
        val lat: Double,
        val lng: Double,
) {
    constructor() : this(lat = 0.0, lng = 0.0)
}