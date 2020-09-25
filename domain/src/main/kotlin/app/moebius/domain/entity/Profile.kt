package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "profile")
data class Profile(
        @Id @GeneratedValue @Column(name = "profile_uuid") val profileUUID: UUID,
        val name: String,
        val surname: String,
        val nickname: String,
        val biography: String,
        val phone: Phone,
        val birthdate: Date,
        val location: Location,
        val sex: String,
        val gender: Gender,
        val avatarUrl: String,
)

@Entity
@Table(name = "phone")
data class Phone(
        @Id @GeneratedValue @Column(name = "phone_uuid") val phoneUUID: UUID,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

@Entity
@Table(name = "date")
data class Date(
        @Id @GeneratedValue @Column(name = "date_uuid") val dateUUID: UUID,
        val day: Int,
        val month: Int,
        val year: Int
)