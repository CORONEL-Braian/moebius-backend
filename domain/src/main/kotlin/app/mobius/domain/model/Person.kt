package app.mobius.domain.model

import app.mobius.domain.entity.role.Role
import app.mobius.domain.model.setting.Setting
import java.util.*
import javax.persistence.*

/**
 * Be endowed with reason, self-aware and possessed of their own identity
 */
@Entity
@Table(name = "person")
data class Person(
        @Id @GeneratedValue @Column(name = "identity_uuid") val identityUUID: UUID? = null,
        val username: String,
        val profile: Profile,
        val setting: Setting,
        val role: Role,
)

