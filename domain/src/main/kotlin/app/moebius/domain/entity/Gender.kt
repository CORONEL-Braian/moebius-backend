package app.moebius.domain.entity

import java.util.*

data class Gender(
        val genderUUID: UUID,
        val type: String,
        val description: String,
        val iconUrl: String
)