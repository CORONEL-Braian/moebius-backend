package app.moebius.domain.model

import app.moebius.domain.model.rol.Rol
import java.util.*

data class User(
        val userUUID: UUID,
        val username: String,
        val account: Account,
        val profile: Profile,
        val rol: Rol
)