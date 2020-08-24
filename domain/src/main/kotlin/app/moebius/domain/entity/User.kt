package app.moebius.domain.entity

import app.moebius.domain.model.rol.Rol
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
        val userId: Int,
        val username: String,
        val account: Account,
        val profile: Profile,
        val rol: Rol
)