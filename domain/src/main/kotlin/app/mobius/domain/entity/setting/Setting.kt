package app.mobius.domain.entity.setting

import app.mobius.domain.entity.Account
import app.mobius.domain.entity.security.Security
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "setting")
data class Setting(
        @Id @GeneratedValue @Column(name = "setting_uuid") val settingUUID: UUID,
        val account: Account,
        val security: Security,
        val theme: Theme
)

enum class Theme {
    LIGHT, DARK, DEFAULT
}