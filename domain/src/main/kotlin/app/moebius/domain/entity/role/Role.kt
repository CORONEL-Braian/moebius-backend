package app.moebius.domain.entity.role

import app.moebius.domain.entity.security.StatusLiveness
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "role")
data class Role(
        val roleUUID: UUID,
        val statusLiveness: StatusLiveness = StatusLiveness.UNSOLICITED,
        val securityLevel: Int = 0,
        val subscription: Subscription,
        val permissions: List<Permission>
)
