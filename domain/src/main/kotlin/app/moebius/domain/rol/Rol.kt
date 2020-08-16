package app.moebius.domain.rol

data class Rol(
        val roleId: Int,
        val isAlive: Boolean,
        val securityLevel: Int,
        val subscription: Subscription,
        val permissions: List<Permission>
)