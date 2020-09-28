package app.mobius.domain.model.setting

import app.mobius.domain.model.Account
import app.mobius.domain.model.security.Security
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "setting")
data class Setting(
        @Id @GeneratedValue @Column(name = "setting_uuid") val settingUUID: UUID? = null,
        val account: Account,
        val security: Security,
        val theme: Theme
)

enum class Theme {
    LIGHT, DARK, DEFAULT
}