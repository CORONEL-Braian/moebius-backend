package app.moebius.domain.entity

import javax.persistence.Entity
import java.util.*

@Entity
data class Profile(
        val profileUUID: UUID,
        val name: String,
        val surname: String,
        val originLocality: String,
        val phone: Phone,
        val birthdate: Birthdate,
        val nationality: String,
        val sex: String,
        val avatarUrl: String,
)

@Entity
data class Phone(
        val phoneUUID: UUID,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

@Entity
data class Birthdate(
        val birhdateUUID: UUID,
        val day: Int,
        val month: Int,
        val year: Int
)