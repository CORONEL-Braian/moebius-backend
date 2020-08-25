package app.moebius.domain.entity.rol

data class Rol(
        val roleUUID: Int,
        val isAlive: Boolean,
        val securityLevel: Int,
        val subscription: Subscription,
        val permissions: List<Permission>
)