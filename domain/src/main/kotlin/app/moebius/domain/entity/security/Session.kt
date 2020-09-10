package app.moebius.domain.entity.security

import java.util.*
import java.util.Date
import javax.persistence.Entity
import javax.persistence.Table

/**
 * TODO: Check whether to rename some type of authorization
 */
@Entity
@Table(name = "session")
data class Session(
        val sessionUUID: UUID,
        val token: AccessToken,
        val dailyReloadToken: DailyReloadToken,
        val monthlyReloadToken: MonthlyReloadToken,
)

/**
 * Represents the access token or AT
 * Expires in 5 minutes
 * @param accessToken: Access token or API key to identify the person
 */
@Entity
@Table(name = "access_token")
data class AccessToken(
        val tokenUUID: UUID,
        val accessToken: Token,
)

/**
 * Represents a daily token to reload the AT
 * Expires in 1 day but is optional for the identity
 */
@Entity
@Table(name = "daily_reload_token")
data class DailyReloadToken(
        val dailyAccessTokenUUID: UUID,
        val keepSessionDaily: Boolean = false,
        val reloadAccessToken: Token? = null
)

/**
 * Represents a monthly token to reload the AT
 * Obs: Expires in 1 month
 */
@Entity
@Table(name = "monthly_reload_token")
data class MonthlyReloadToken(
        val dailyAccessTokenUUID: UUID,
        val reloadAccessToken: Token? = null
)

@Entity
@Table(name = "token")
data class Token(
        val tokenUUID: UUID,
        val token: String,
        val created: Date,
        val expiry: Date
)