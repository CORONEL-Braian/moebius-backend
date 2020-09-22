package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "profile")
data class Profile(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val profileUUID: UUID,
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
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val phoneUUID: UUID,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

@Entity
@Table(name = "date")
data class Date(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val dateUUID: UUID,
        val day: Int,
        val month: Int,
        val year: Int
)