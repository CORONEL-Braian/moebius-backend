package app.moebius.domain.rol

data class Rol(
        val roleId: Int,
        val isAlive: Boolean,
        val isIdentityValidated: Boolean,
        val permissions: List<Permission>
)
