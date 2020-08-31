package app.moebius.domain.object_values

import java.util.*

data class Gender(
        val genderUUID: UUID,
        val type: String,
        val description: String
)