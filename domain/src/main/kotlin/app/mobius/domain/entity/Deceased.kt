package app.mobius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "deceased")
data class Deceased(
        @Id @GeneratedValue @Column(name = "deceased_uuid") val deceasedUUID: UUID,
        val dni: Int,
        val provider: String,
        val registrationDatetime: String,
        val datetime: String,
        val country: String,
        val province: String,
        val locality: String,
        val observation: String,
)