package app.moebius.domain.entity

import app.moebius.domain.entity.role.Role
import app.moebius.domain.entity.setting.Setting
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

