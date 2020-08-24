package app.moebius.domain.model

data class Deceased(
        val deceasedId: Int,
        val dni: Int,
        val provider: String,
        val registrationDatetime: String,
        val datetime: String,
        val country: String,
        val province: String,
        val locality: String,
        val observation: String,
)