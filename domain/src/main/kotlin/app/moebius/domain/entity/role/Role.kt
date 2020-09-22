package app.moebius.domain.entity.role

import app.moebius.domain.entity.security.StatusLiveness
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val roleUUID: UUID,
        val statusLiveness: StatusLiveness = StatusLiveness.UNSOLICITED,
        val securityLevel: Int = 0,
        val subscription: Subscription,
        val permissions: List<Permission>
)
