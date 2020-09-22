package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "gender")
data class Gender(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val genderUUID: UUID,
        val type: String,
        val description: String,
        val iconUrl: String
)