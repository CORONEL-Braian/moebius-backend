package app.mobius.domain.entity.profile

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "phone")
data class Phone(
        @Id @GeneratedValue @Column(name = "phone_uuid") val phoneUUID: UUID? = null,
        @Column(name = "code_country") var codeCountry: String,
        @Column(name = "code_area") var codeArea: String,
        @Column(name = "number") var number: Long
) {
    constructor() : this(codeCountry = "", codeArea = "", number = -1)
}

enum class Sex {
    F, M
}