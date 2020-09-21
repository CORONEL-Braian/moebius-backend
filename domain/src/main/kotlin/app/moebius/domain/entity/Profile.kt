package app.moebius.domain.entity

import javax.persistence.Entity
import java.util.*
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "profile")
data class Profile(
        @Id val profileUUID: UUID,
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
        @Id val phoneUUID: UUID,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

@Entity
@Table(name = "date")
data class Date(
        @Id val dateUUID: UUID,
        val day: Int,
        val month: Int,
        val year: Int
)