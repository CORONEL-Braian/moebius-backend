package app.mobius.domain.entity.setting.security

import java.util.*
import javax.persistence.*

/**
 * TODO: Check whether to rename some type of authorization
 */
@Entity
@Table(name = "session")
data class Session(
        @Id @GeneratedValue @Column(name = "sessionUUID") val sessionUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "accessTokenUUID", referencedColumnName = "accessTokenUUID")
        val accessToken: AccessToken,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "dailyReloadTokenUUID", referencedColumnName = "dailyReloadTokenUUID")
        val dailyReloadToken: DailyReloadToken,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "monthlyReloadTokenUUID", referencedColumnName = "monthlyReloadTokenUUID")
        val monthlyReloadToken: MonthlyReloadToken,
) {
    constructor() : this(accessToken = AccessToken(), dailyReloadToken = DailyReloadToken(), monthlyReloadToken = MonthlyReloadToken())
}

/**
 * Represents the access token or AT
 * Expires in 5 minutes
 * OBS: Dont use AccessToken as name to avoid conflicts of mapping TODO
 * @param token: Access token or API key to identify the person
 */
@Entity
@Table(name = "accessToken")
data class AccessToken(
        @Id @GeneratedValue @Column(name = "accessTokenUUID") val tokenUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "tokenUUID", referencedColumnName = "tokenUUID")
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
        @Id @GeneratedValue @Column(name = "dailyReloadTokenUUID") val dailyAccessTokenUUID: UUID? = null,
        @Column(name = "keepSessionDaily") val keepSessionDaily: Boolean = false,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "tokenUUID", referencedColumnName = "tokenUUID")
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
        @Id @GeneratedValue @Column(name = "monthlyReloadTokenUUID") val dailyAccessTokenUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "tokenUUID", referencedColumnName = "tokenUUID")
        val token: Token
) {
    constructor() : this(token = Token())
}