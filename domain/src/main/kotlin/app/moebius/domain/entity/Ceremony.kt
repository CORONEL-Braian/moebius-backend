package app.moebius.domain.entity

import java.util.*

data class Ceremony(
        val ceremonyUUID: UUID,
        val type: Type
)

data class Type(
        val typeUUID: UUID,
        val name: String
)

//test
