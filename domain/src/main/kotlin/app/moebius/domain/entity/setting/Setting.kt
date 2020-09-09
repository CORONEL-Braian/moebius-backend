package app.moebius.domain.entity.setting

import java.util.*

data class Setting(
        val settingUUID: UUID,
        val theme: Theme
)

enum class Theme {
    LIGHT, DARK, DEFAULT
}