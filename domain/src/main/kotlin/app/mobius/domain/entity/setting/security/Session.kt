package app.mobius.domain.entity.setting.security

import java.util.*
import java.util.Date
import javax.persistence.*

/**
 * TODO: Check whether to rename some type of authorization
 */
@Entity
@Table(name = "session")
data class Session(
        @Id @GeneratedValue @Column(name = "sessionUUID") val sessionUUID: UUID? = null,
        val accessToken: MyAccessToken,
        val dailyReloadToken: DailyReloadToken,
        val monthlyReloadToken: MonthlyReloadToken,
) {
    constructor() : this()
}

/**
 * Represents the access token or AT
 * Expires in 5 minutes
 * OBS: Dont use AccessToken as name to avoid conflicts of mapping
 * @param accessToken: Access token or API key to identify the person
 */
@Entity
@Table(name = "accessToken")
data class MyAccessToken(
        @Id @GeneratedValue @Column(name = "accessTokenUUID") val tokenUUID: UUID? = null,
        @Column(name = "accessToken") val accessToken: Token,
) {
    constructor() : this()
}

/**
 * Represents a daily token to reload the AT
 * Expires in 1 day but is optional for the person
 */
@Entity
@Table(name = "dailyReloadToken")
data class DailyReloadToken(
        @Id @GeneratedValue @Column(name = "dailyReloadTokenUUID") val dailyAccessTokenUUID: UUID? = null,
        val keepSessionDaily: Boolean = false,
        @Column(name = "dailyReloadToken") val reloadAccessToken: Token? = null
) {
    constructor() : this()
}

/**
 * Represents a monthly token to reload the AT
 * Obs: Expires in 1 month
 */
@Entity
@Table(name = "monthlyReloadToken")
data class MonthlyReloadToken(
        @Id @GeneratedValue @Column(name = "monthlyReloadTokenUUID") val dailyAccessTokenUUID: UUID? = null,
        val reloadAccessToken: Token? = null
) {
    constructor() : this()
}

@Entity
@Table(name = "token")
data class Token(
        @Id @GeneratedValue @Column(name = "tokenUUID") val tokenUUID: UUID? = null,
        val token: String,
        val created: Date = Date(),
        val expiry: Date
) {
    constructor() : this(token = "", expiry = Date())
}