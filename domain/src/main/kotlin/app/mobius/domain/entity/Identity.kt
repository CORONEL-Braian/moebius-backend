package app.mobius.domain.entity

import app.mobius.domain.entity.role.Role
import app.mobius.domain.entity.setting.Setting
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "identity")
data class Identity(
        @Id @GeneratedValue @Column(name = "identity_uuid") val identityUUID: UUID,
        val identityname: String,
        val profile: Profile,
        val setting: Setting,
        val role: Role,
)

