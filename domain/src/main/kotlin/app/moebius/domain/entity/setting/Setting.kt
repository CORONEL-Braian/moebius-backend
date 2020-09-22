package app.moebius.domain.entity.setting

import app.moebius.domain.entity.Account
import app.moebius.domain.entity.security.Security
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "setting")
data class Setting(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val settingUUID: UUID,
        val account: Account,
        val security: Security,
        val theme: Theme
)

enum class Theme {
    LIGHT, DARK, DEFAULT
}