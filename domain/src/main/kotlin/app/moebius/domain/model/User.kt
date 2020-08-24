package app.moebius.domain.model

import app.moebius.domain.model.rol.Rol

data class User(
        val userId: Int,
        val profile: Profile,
        val account: Account,
        val username: String,
        val rol: Rol
)