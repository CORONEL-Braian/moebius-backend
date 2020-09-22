package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "location")
data class Location(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val locationUUID: UUID,
        val originLocation: OriginLocation,
        val currentLocation: CurrentLocation
)

@Entity
@Table(name = "origin_location")
data class OriginLocation(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val originLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City
)

@Entity
@Table(name = "current_location")
data class CurrentLocation(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val currentLocationUUID: UUID,
        val country: Country,
        val province: Province,
        val city: City,
        val direction: Direction? = null
)

@Entity
@Table(name = "country")
data class Country(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val countryUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "province")
data class Province(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val provinceUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "city")
data class City(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val cityUUID: UUID,
        val name: String,
        val locationLimits: LocationLimits,
)

@Entity
@Table(name = "location_limits")
data class LocationLimits(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val limitsUUID: UUID,
        val northeast: Coordinate,
        val southwest: Coordinate
)

@Entity
@Table(name = "direction")
data class Direction(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val directionUUID: UUID,
        val street: String,
        val number: Int,
        val firstBetweenStreet: String,
        val secondBetweenStreet: String,
        val coordinate: Coordinate
)

@Entity
@Table(name = "coordinate")
data class Coordinate(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val lat: Float,
        val lng: Float,
)