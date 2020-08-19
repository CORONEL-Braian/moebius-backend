package app.moebius.domain.model.rol

data class Rol(
        val roleId: Int,
        val isAlive: Boolean = true,
        val securityLevel: Int = 0,
        val subscription: Subscription,
        val permissions: List<Permission>? = null   //TODO: Not null by default
)