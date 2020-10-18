package app.mobius.domain.entity.setting.security

import org.hibernate.annotations.Generated
import org.hibernate.annotations.GenerationTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "token")
data class Token(
        @Id @GeneratedValue @Column(name = "token_uuid") val tokenUUID: UUID? = null,
        @Generated(GenerationTime.INSERT) val token: String? = null,
        @Column(name = "created") val created: Date = Date(),
        @Column(name = "expiry") val expiry: Date
) {
    constructor() : this(created = Date(), expiry = Date())
}