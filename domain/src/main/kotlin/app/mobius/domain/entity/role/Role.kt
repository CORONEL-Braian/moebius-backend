package app.mobius.domain.entity.role

import app.mobius.domain.entity.security.StatusLiveness
import app.mobius.domain.mapper.role.Permission
import app.mobius.domain.mapper.role.Subscription
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "role")
data class Role(
        @Id @GeneratedValue @Column(name = "role_uuid") val roleUUID: UUID,
        val statusLiveness: StatusLiveness = StatusLiveness.UNSOLICITED,
        val securityLevel: Int = 0,
        val subscription: Subscription,
        val permissions: List<Permission>
)
