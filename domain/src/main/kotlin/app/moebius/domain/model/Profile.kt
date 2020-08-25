package app.moebius.domain.model

import java.util.*

data class Profile(
        val profileUUID: UUID,
        val dni: Int,
        val identificationNumber: Int,
        val name: String,
        val surname: String,
        val originLocality: String,
        val phone: Phone,
        val birthdate: Birthdate,
        val nationality: String,
        val sex: String,
        val avatarUrl: String,
        val isIdentityValidated: String,
)

data class Phone(
        val phoneUUID: UUID,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

data class Birthdate(
        val birhdateUUID: UUID,
        val day: Int,
        val month: Int,
        val year: Int
)