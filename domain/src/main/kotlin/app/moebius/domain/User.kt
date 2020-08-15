package app.moebius.domain

import app.moebius.domain.rol.Rol

data class User(
        val userId: Int,
        val profile: Profile,
        val account: Account,
        val username: String,
        val rol: Rol
)