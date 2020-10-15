package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "profile")
data class Profile(
        @Id @GeneratedValue @Column(name = "profile_uuid") val profileUUID: UUID? = null,
        val name: String,
        val surname: String,
        val nickname: String,
        val biography: String,
        @Column(name = "phone") val phone: Phone,
//        val birthdate: Date,  TODO: Enable
//        val location: Location,   TODO: Enable
        val sex: String,
//        val gender: Gender,   TODO: Enable
        val avatarUrl: String,
)

@Entity
@Table(name = "phone")
data class Phone(
        @Id @GeneratedValue @Column(name = "phone_uuid") val phoneUUID: UUID? = null,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

@Entity
@Table(name = "date")
data class Date(
        @Id @GeneratedValue @Column(name = "date_uuid") val dateUUID: UUID? = null,
         val day: Int,
         val month: Int,
         val year: Int
)