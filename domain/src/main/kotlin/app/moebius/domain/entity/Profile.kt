package app.moebius.domain.entity

import javax.persistence.Entity
import java.util.*
import javax.persistence.Table

@Entity
@Table(name = "profile")
data class Profile(
        val profileUUID: UUID,
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
        val phoneUUID: UUID,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

@Entity
@Table(name = "date")
data class Date(
        val dateUUID: UUID,
        val day: Int,
        val month: Int,
        val year: Int
)