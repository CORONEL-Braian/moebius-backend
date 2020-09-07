package app.moebius.domain.entity.security

import java.util.*

/**
 * @param securityLevel: [0,4]
 */
data class Security(
        val securityUUID: UUID,
        val securityLevel: Int = 0,
        val token: Token,
        val securityMethods: SecurityMethods? = null,
)

/**
 * Expires in 5 minutes
 * @param token: Access token or API key to identify the user
 */
data class AccessToken(
        val tokenUUID: UUID,
        val accessToken: Token,
)

/**
 * Expires in 1 day
 * In case the user does not enable the reload, only the fingerprint will be used.
 * @param enable:
 * @param reloadAccessToken: Reload the access token
 */
data class ReloadAccessToken(
        val dailyAccessTokenUUID: UUID,
        val enable: Boolean = false,
        val reloadAccessToken: Token? = null
)

data class Token(
        val token: String,
        val created: Date,
        val expiry: Date
)

data class SecurityMethods(
        val securityMethodsUUID: UUID,
        val identityVerification: IdentityVerification? = null,
        val twoFA: TwoFA? = null,
        val antiPishingCode: AntiPishingCode? = null,
        val reloadAccessToken: ReloadAccessToken? = null
)

data class TwoFA(
        val twoFAUUID: UUID,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton?,
        val smsAuthentication: SMSAuthentication?,
        val emailVerification: EmailVerification?
)

data class GoogleAuthenticaton(
        val googleAuthenticationUUID: UUID,
        val verificationCode: Int
)

data class SMSAuthentication(
        val smsAuthenticationUUID: UUID,
        val verificationCode: Int
)

data class EmailVerification(
        val emailVerificationUUID: UUID,
        val isEmailVerified: Boolean,
        val emailVerificationToken: String,
)

data class AntiPishingCode(
        val antiPishingCodeUUID: UUID,
        val enable: Boolean,
        val code: String? = null
)