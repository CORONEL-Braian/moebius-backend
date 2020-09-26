package app.mobius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "location")
data class Location(
        @Id @GeneratedValue @Column(name = "location_uuid") val locationUUID: UUID,
        val originLocation: OriginLocation,
        val currentLocation: CurrentLocation
)

@Entity
@Table(name = "origin_location")
data class OriginLocation(
        @Id @GeneratedValue @Column(name = "origin_location_uuid") val originLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City
)

@Entity
@Table(name = "current_location")
data class CurrentLocation(
        @Id @GeneratedValue @Column(name = "current_location_uuid") val currentLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City,
        val direction: Direction? = null
)

@Entity
@Table(name = "country")
data class Country(
        @Id @GeneratedValue @Column(name = "country_uuid") val countryUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "province")
data class Province(
        @Id @GeneratedValue @Column(name = "province_uuid") val provinceUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "city")
data class City(
        @Id @GeneratedValue @Column(name = "city_uuid") val cityUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "location_limits")
data class LocationLimits(
        @Id @GeneratedValue @Column(name = "location_limits_uuid") val limitsUUID: UUID,
        val northeast: Coordinate,
        val southwest: Coordinate
)

@Entity
@Table(name = "direction")
data class Direction(
        @Id @GeneratedValue @Column(name = "direction_uuid") val directionUUID: UUID,
        val street: String,
        val number: Int,
        val firstBetweenStreet: String,
        val secondBetweenStreet: String,
        val coordinate: Coordinate
)

@Entity
@Table(name = "coordinate")
data class Coordinate(
        @Id @GeneratedValue @Column(name = "coordinate_uuid") val lat: Float,
        val lng: Float,
)