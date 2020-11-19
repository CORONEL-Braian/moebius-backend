package app.mobius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "gender")
data class Gender(
        @Id @GeneratedValue @Column(name = "gender_uuid", insertable = false, updatable = false) var genderUUID: UUID? = null,
        @Column(name = "type", insertable = false, updatable = false) var type: String,
        @Column(name = "description", insertable = false, updatable = false) var description: String? = null,
        @Column(name = "icon_url", insertable = false, updatable = false) var iconUrl: String? = null
) {
    constructor() : this(genderUUID = null, type = "")
}