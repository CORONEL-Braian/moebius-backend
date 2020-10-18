package app.mobius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "gender")
data class Gender(
        @Id @GeneratedValue @Column(name = "gender_uuid") val genderUUID: UUID? = null,
        val type: String,
        val description: String? = null,
        val iconUrl: String? = null
) {
    constructor() : this(type = "")
}