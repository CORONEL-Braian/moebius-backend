package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "gender")
data class Gender(
        @Id @GeneratedValue @Column(name = "gender_uuid") val genderUUID: UUID,
        val type: String,
        val description: String,
        val iconUrl: String
)