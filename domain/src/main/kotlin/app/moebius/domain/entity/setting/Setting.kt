package app.moebius.domain.entity.setting

import app.moebius.domain.entity.Account
import app.moebius.domain.entity.security.Security
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "setting")
data class Setting(
        val settingUUID: UUID,
        val account: Account,
        val security: Security,
        val theme: Theme
)

enum class Theme {
    LIGHT, DARK, DEFAULT
}