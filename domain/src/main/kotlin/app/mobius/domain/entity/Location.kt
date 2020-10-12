package app.mobius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "location")
data class Location(
        @Id @GeneratedValue @Column(name = "locationUUID") val currentLocationUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "countryUUID", referencedColumnName = "countryUUID")
        val country: Country,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "provinceUUID", referencedColumnName = "provinceUUID")
        val province: Province,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "cityUUID", referencedColumnName = "cityUUID")
        val city: City,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "directionUUID", referencedColumnName = "directionUUID")
        val direction: Direction? = null
) {
    constructor() : this(country = Country(), province = Province(), city = City())
}

@Entity
@Table(name = "country")
data class Country(
        @Id @GeneratedValue @Column(name = "countryUUID") val countryUUID: UUID? = null,
        @Column(name = "name") val name: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "locationLimitsUUID", referencedColumnName = "locationLimitsUUID")
        val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

@Entity
@Table(name = "province")
data class Province(
        @Id @GeneratedValue @Column(name = "provinceUUID") val provinceUUID: UUID? = null,
        @Column(name = "name") val name: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "locationLimitsUUID", referencedColumnName = "locationLimitsUUID")
        val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

@Entity
@Table(name = "city")
data class City(
        @Id @GeneratedValue @Column(name = "cityUUID") val cityUUID: UUID? = null,
        @Column(name = "name") val name: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "locationLimitsUUID", referencedColumnName = "locationLimitsUUID")
        val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

@Entity
@Table(name = "locationLimits")
data class LocationLimits(
        @Id @GeneratedValue @Column(name = "locationLimitsUUID") val limitsUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "northeastUUID", referencedColumnName = "coordinateUUID")
        val northeast: Coordinate,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "southwestUUID", referencedColumnName = "coordinateUUID")
        val southwest: Coordinate
) {
    constructor() : this(northeast = Coordinate(), southwest = Coordinate())
}

@Entity
@Table(name = "direction")
data class Direction(
        @Id @GeneratedValue @Column(name = "directionUUID") val directionUUID: UUID? = null,
        @Column(name = "street") val street: String,
        @Column(name = "number") val number: Int,
        @Column(name = "firstBetweenStreet") val firstBetweenStreet: String? = null,
        @Column(name = "secondBetweenStreet") val secondBetweenStreet: String? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "coordinateUUID", referencedColumnName = "coordinateUUID")
        val coordinate: Coordinate
) {
    constructor() : this(street = "", number = -1, coordinate = Coordinate())
}

@Entity
@Table(name = "coordinate")
data class Coordinate(
        @Id @GeneratedValue @Column(name = "coordinateUUID") val coordinateUUID: UUID? = null,
        @Column(name = "lat") val lat: Double,
        @Column(name = "lng") val lng: Double,
) {
    constructor() : this(lat = 0.0, lng = 0.0)
}