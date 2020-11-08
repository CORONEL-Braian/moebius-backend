package app.mobius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "location")
data class Location(
        @Id @GeneratedValue @Column(name = "location_uuid") val currentLocationUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "country_uuid", referencedColumnName = "country_uuid")
        val country: Country,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "province_uuid", referencedColumnName = "province_uuid")
        val province: Province,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "city_uuid", referencedColumnName = "city_uuid")
        val city: City,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "direction_uuid", referencedColumnName = "direction_uuid")
        val direction: Direction? = null
) {
    constructor() : this(country = Country(), province = Province(), city = City())
}

@Entity
@Table(name = "country")
data class Country(
        @Id @GeneratedValue @Column(name = "country_uuid") val countryUUID: UUID? = null,
        @Column(name = "name") val name: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "location_limits_uuid", referencedColumnName = "location_limits_uuid")
        val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

@Entity
@Table(name = "province")
data class Province(
        @Id @GeneratedValue @Column(name = "province_uuid") val provinceUUID: UUID? = null,
        @Column(name = "name") val name: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "location_limits_uuid", referencedColumnName = "location_limits_uuid")
        val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

@Entity
@Table(name = "city")
data class City(
        @Id @GeneratedValue @Column(name = "city_uuid") val cityUUID: UUID? = null,
        @Column(name = "name") val name: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "location_limits_uuid", referencedColumnName = "location_limits_uuid")
        val locationLimits: LocationLimits,
) {
    constructor() : this(name = "", locationLimits = LocationLimits())
}

@Entity
@Table(name = "location_limits")
data class LocationLimits(
        @Id @GeneratedValue @Column(name = "location_limits_uuid") val limitsUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "northeast_uuid", referencedColumnName = "coordinate_uuid")
        val northeast: Coordinate,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "southwest_uuid", referencedColumnName = "coordinate_uuid")
        val southwest: Coordinate
) {
    constructor() : this(northeast = Coordinate(), southwest = Coordinate())
}

@Entity
@Table(name = "direction")
data class Direction(
        @Id @GeneratedValue @Column(name = "direction_uuid") val directionUUID: UUID? = null,
        val street: String,
        val number: Int,
        val firstBetweenStreet: String? = null,
        val secondBetweenStreet: String? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "coordinate_uuid", referencedColumnName = "coordinate_uuid")
        val coordinate: Coordinate
) {
    constructor() : this(street = "", number = -1, coordinate = Coordinate())
}

@Entity
@Table(name = "coordinate")
data class Coordinate(
        @Id @GeneratedValue @Column(name = "coordinate_uuid") val coordinateUUID: UUID? = null,
        val lat: Double,
        val lng: Double,
) {
    constructor() : this(lat = 0.0, lng = 0.0)
}