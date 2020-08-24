package app.moebius.domain.entity

data class Security(
        val securityId: Int,
        val securityLevel: Int = 0,
        val securityMethods: List<SecurityMethods>? = null
)

interface SecurityMethods

data class TwoFA(
        val twoFAId: Int,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton?,
        val smsAuthentication: SMSAuthentication?,
        val emailVerification: EmailVerification?
): SecurityMethods

data class GoogleAuthenticaton(
        val googleAuthenticationId: Int,
        val verificationCode: Int
)

data class SMSAuthentication(
        val smsAuthenticationId: Int
)

data class EmailVerification(
        val emailVerificationId: Int,
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
        val antiPishingCodeId: Int,
        val enable: Boolean,
        val code: String?
): SecurityMethods