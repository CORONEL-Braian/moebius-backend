package app.mobius.domain.entity.setting.security

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "token")
data class Token(
        @Id @GeneratedValue @Column(name = "token_uuid") val tokenUUID: UUID? = null,
        @Column(name = "token") val token: String,
        @Column(name = "created") val created: Date = Date(),
        @Column(name = "expiry") val expiry: Date
) {
    constructor() : this(token = "", created = Date(), expiry = Date())
}