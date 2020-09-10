package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "deceased")
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