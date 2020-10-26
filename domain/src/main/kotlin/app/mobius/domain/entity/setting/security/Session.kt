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
        @JoinColumn(name = "accessToken_uuid", referencedColumnName = "accessToken_uuid")
        val accessToken: AccessToken,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "dailyReloadToken_uuid", referencedColumnName = "dailyReloadToken_uuid")
        val dailyReloadToken: DailyReloadToken,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "monthlyReloadToken_uuid", referencedColumnName = "monthlyReloadToken_uuid")
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
@Table(name = "accessToken")
data class AccessToken(
        @Id @GeneratedValue @Column(name = "accessToken_uuid") val tokenUUID: UUID? = null,

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
@Table(name = "dailyReloadToken")
data class DailyReloadToken(
        @Id @GeneratedValue @Column(name = "dailyReloadToken_uuid") val dailyAccessTokenUUID: UUID? = null,
        @Column(name = "keepSessionDaily") val keepSessionDaily: Boolean = false,

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
@Table(name = "monthlyReloadToken")
data class MonthlyReloadToken(
        @Id @GeneratedValue @Column(name = "monthlyReloadToken_uuid") val dailyAccessTokenUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "token_uuid", referencedColumnName = "token_uuid")
        val token: Token
) {
    constructor() : this(token = Token())
}