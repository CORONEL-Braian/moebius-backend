package app.moebius.domain.object_values

import app.moebius.domain.object_values.rol.Rol
import java.util.*

data class User(
        val userUUID: UUID,
        val username: String,
        val account: Account,
        val profile: Profile,
        val rol: Rol
)