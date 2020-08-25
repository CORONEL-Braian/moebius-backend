package app.moebius.domain.entity

data class Security(
        val securityUUID: Int,
        val securityLevel: Int = 0,
        val securityMethods: List<SecurityMethods>? = null
)

interface SecurityMethods

data class TwoFA(
        val twoFAUUID: Int,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton?,
        val smsAuthentication: SMSAuthentication?,
        val emailVerification: EmailVerification?
): SecurityMethods

data class GoogleAuthenticaton(
        val googleAuthenticationUUID: Int,
        val verificationCode: Int
)

data class SMSAuthentication(
        val smsAuthenticationUUID: Int
)

data class EmailVerification(
        val emailVerificationUUID: Int,
        val isEmailVerified: Boolean,
        val emailVerificationToken: String,
)

data class IdentityVerification(
        val id: Int,
        val state: State,
): SecurityMethods

enum class State {
    PENDING, VERIFIED, BLOCKED
}

data class AntiPishingCode(
        val antiPishingCodeUUID: Int,
        val enable: Boolean,
        val code: String?
): SecurityMethods