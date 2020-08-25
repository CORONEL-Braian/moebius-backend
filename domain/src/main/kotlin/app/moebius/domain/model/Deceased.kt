package app.moebius.domain.model

data class Deceased(
        val deceasedUUID: Int,
        val dni: Int,
        val provider: String,
        val registrationDatetime: String,
        val datetime: String,
        val country: String,
        val province: String,
        val locality: String,
        val observation: String,
)