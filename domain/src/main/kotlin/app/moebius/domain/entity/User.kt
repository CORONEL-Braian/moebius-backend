package app.moebius.domain.entity

import app.moebius.domain.model.rol.Rol
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
        val userId: Int,
        val profile: Profile,
        val account: Account,
        val username: String,
        val rol: Rol
)