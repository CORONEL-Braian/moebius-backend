package app.moebius.domain.object_values.role

import app.moebius.domain.object_values.security.StatusLiveness
import java.util.*

data class Role(
        val roleUUID: UUID,
        val statusLiveness: StatusLiveness,
        val securityLevel: Int = 0,
        val subscription: Subscription,
        val permissions: List<Permission>? = null
)