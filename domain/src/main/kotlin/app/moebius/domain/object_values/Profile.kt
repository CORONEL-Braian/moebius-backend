package app.moebius.domain.object_values

import java.util.*

data class Profile(
        val profileUUID: UUID,
        val name: String,
        val surname: String,
        val nickname: String,
        val biography: String,
        val phone: Phone,
        val birthdate: Date,
        val nationality: String,
        val sex: String,
        val gender: Gender,
        val avatarUrl: String,
)


data class Phone(
        val phoneUUID: UUID,
        val codeCountry: String,
        val codeArea: String,
        val number: Int
)

data class Date(
        val dateUUID: UUID,
        val day: Int,
        val month: Int,
        val year: Int
)