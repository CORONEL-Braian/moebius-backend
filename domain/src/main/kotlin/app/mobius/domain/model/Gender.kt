package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "gender")
data class Gender(
        @Id @GeneratedValue @Column(name = "genderUUID") val genderUUID: UUID? = null,
        val type: String,
        val description: String,
        val iconUrl: String
)