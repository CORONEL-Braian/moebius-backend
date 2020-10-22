package app.mobius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "gender")
data class Gender(
        @Id @GeneratedValue @Column(name = "gender_uuid", insertable = false, updatable = false) var genderUUID: UUID? = null,
        @Column(insertable = false, updatable = false) var type: String,
        @Column(insertable = false, updatable = false) var description: String? = null,
        @Column(insertable = false, updatable = false) var iconUrl: String? = null
) {
    constructor(genderUUID: UUID?) : this(genderUUID = genderUUID, type = "")
}