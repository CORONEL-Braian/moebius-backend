package app.mobius.domain.model.security

import java.util.*
import java.util.Date
import javax.persistence.*

/**
 * TODO: Check whether to rename some type of authorization
 */
@Entity
@Table(name = "session")
data class Session(
        @Id @GeneratedValue @Column(name = "session_uuid") val sessionUUID: UUID? = null,
        val accessToken: MyAccessToken,
        val dailyReloadToken: DailyReloadToken,
        val monthlyReloadToken: MonthlyReloadToken,
)

/**
 * Represents the access token or AT
 * Expires in 5 minutes
 * OBS: Dont use AccessToken as name to avoid conflicts of mapping
 * @param accessToken: Access token or API key to identify the person
 */
@Entity
@Table(name = "access_token")
data class MyAccessToken(
        @Id @GeneratedValue @Column(name = "access_token_uuid") val tokenUUID: UUID? = null,
        @Column(name = "access_token") val accessToken: Token,
)

/**
 * Represents a daily token to reload the AT
 * Expires in 1 day but is optional for the identity
 */
@Entity
@Table(name = "daily_reload_token")
data class DailyReloadToken(
        @Id @GeneratedValue @Column(name = "daily_reload_token_uuid") val dailyAccessTokenUUID: UUID? = null,
        val keepSessionDaily: Boolean = false,
        @Column(name = "reload_access_token") val reloadAccessToken: Token? = null
)

/**
 * Represents a monthly token to reload the AT
 * Obs: Expires in 1 month
 */
@Entity
@Table(name = "monthly_reload_token")
data class MonthlyReloadToken(
        @Id @GeneratedValue @Column(name = "monthly_reload_token_uuid") val dailyAccessTokenUUID: UUID? = null,
        val reloadAccessToken: Token? = null
)

@Entity
@Table(name = "token")
data class Token(
        @Id @GeneratedValue @Column(name = "token_uuid") val tokenUUID: UUID? = null,
        val token: String,
        val created: Date,
        val expiry: Date
)