package app.moebius.domain.entity.security

import java.util.*
import java.util.Date

/**
 * TODO: Check if rename to type of auth
 */
data class Session(
        val sessionUUID: UUID,
        val token: AccessToken,
        val dailyReloadToken: DailyReloadToken,
        val monthlyReloadToken: MonthlyReloadToken,
)

/**
 * Represents the access token or AT
 * Expires in 5 minutes
 * @param accessToken: Access token or API key to identify the user
 */
data class AccessToken(
        val tokenUUID: UUID,
        val accessToken: Token,
)

/**
 * Represents a daily token to reload the AT
 * Expires in 1 day but is optional for the user
 */
data class DailyReloadToken(
        val dailyAccessTokenUUID: UUID,
        val keepSessionDaily: Boolean = false,
        val reloadAccessToken: Token? = null
)

/**
 * Represents a monthly token to reload the AT
 * Obs: Expires in 1 month
 */
data class MonthlyReloadToken(
        val dailyAccessTokenUUID: UUID,
        val reloadAccessToken: Token? = null
)

data class Token(
        val tokenUUID: UUID,
        val token: String,
        val created: Date,
        val expiry: Date
)