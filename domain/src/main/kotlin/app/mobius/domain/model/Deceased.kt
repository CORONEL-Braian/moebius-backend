package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "deceased")
data class Deceased(
        @Id @GeneratedValue @Column(name = "deceasedUUID") val deceasedUUID: UUID? = null,
        val dni: Int,
        val provider: String,
        val registrationDatetime: String,
        val datetime: String,
        val country: String,
        val province: String,
        val locality: String,
        val observation: String,
)