package app.moebius.domain.model.rol

import java.util.*

data class Rol(
        val roleUUID: UUID,
        val isAlive: Boolean = true,
        val securityLevel: Int = 0,
        val subscription: Subscription,
        val permissions: List<Permission>? = null   //TODO: Not null by default
)