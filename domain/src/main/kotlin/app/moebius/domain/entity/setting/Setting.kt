package app.moebius.domain.entity.setting

import app.moebius.domain.entity.Account
import app.moebius.domain.entity.security.Security
import java.util.*

data class Setting(
        val settingUUID: UUID,
        val account: Account,
        val security: Security,
        val theme: Theme
)

enum class Theme {
    LIGHT, DARK, DEFAULT
}