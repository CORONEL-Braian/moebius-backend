package app.moebius.domain.entity

import app.moebius.domain.entity.role.Role
import app.moebius.domain.entity.security.Security
import app.moebius.domain.entity.setting.Setting
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "user")
data class User(
        val userUUID: UUID,
        val username: String,
        val account: Account,
        val profile: Profile,
        val setting: Setting,
        val security: Security,
        val role: Role,
)

