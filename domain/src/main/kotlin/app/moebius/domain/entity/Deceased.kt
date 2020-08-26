package app.moebius.domain.entity

import java.util.*

data class Deceased(
        val deceasedUUID: UUID,
        val dni: Int,
        val provider: String,
        val registrationDatetime: String,
        val datetime: String,
        val country: String,
        val province: String,
        val locality: String,
        val observation: String,
)