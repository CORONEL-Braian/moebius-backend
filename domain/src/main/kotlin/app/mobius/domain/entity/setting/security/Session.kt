package app.mobius.domain.entity.setting.security

import java.util.*
import javax.persistence.*

/**
 * TODO: Check whether to rename some type of authorization
 */
@Entity
@Table(name = "session")
data class Session(
        @Id @GeneratedValue @Column(name = "session_uuid") val sessionUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "access_token_uuid", referencedColumnName = "access_token_uuid")
        val accessToken: AccessToken,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "daily_reload_token_uuid", referencedColumnName = "daily_reload_token_uuid")
        val dailyReloadToken: DailyReloadToken,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "monthly_reload_token_uuid", referencedColumnName = "monthly_reload_token_uuid")
        val monthlyReloadToken: MonthlyReloadToken,
) {
    constructor() : this(accessToken = AccessToken(), dailyReloadToken = DailyReloadToken(), monthlyReloadToken = MonthlyReloadToken())
}

/**
 * Represents the data_access token or AT
 * Expires in 5 minutes
 * OBS: Dont use AccessToken as name to avoid conflicts of mapping TODO
 * @param token: Access token or API key to identify the person
 */
@Entity
@Table(name = "access_token")
data class AccessToken(
        @Id @GeneratedValue @Column(name = "access_token_uuid") val tokenUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "token_uuid", referencedColumnName = "token_uuid")
        val token: Token
) {
    constructor() : this(token = Token())
}

/**
 * Represents a daily token to reload the AT
 * @param keepSessionDaily: Expires in 1 day but is optional for the person
 */
@Entity
@Table(name = "daily_reload_token")
data class DailyReloadToken(
        @Id @GeneratedValue @Column(name = "daily_reload_token_uuid") val dailyAccessTokenUUID: UUID? = null,
        @Column(name = "keep_session_daily") val keepSessionDaily: Boolean = false,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "token_uuid", referencedColumnName = "token_uuid")
        val token: Token
) {
    constructor() : this(token = Token())
}

/**
 * Represents a monthly token to reload the AT
 * Obs: Expires in 1 month
 */
@Entity
@Table(name = "monthly_reload_token")
data class MonthlyReloadToken(
        @Id @GeneratedValue @Column(name = "monthly_reload_token_uuid") val dailyAccessTokenUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "token_uuid", referencedColumnName = "token_uuid")
        val token: Token
) {
    constructor() : this(token = Token())
}