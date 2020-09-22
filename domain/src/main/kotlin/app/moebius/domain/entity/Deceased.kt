package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "deceased")
data class Deceased(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val deceasedUUID: UUID,
        val dni: Int,
        val provider: String,
        val registrationDatetime: String,
        val datetime: String,
        val country: String,
        val province: String,
        val locality: String,
        val observation: String,
)