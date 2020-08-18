package app.moebius.domain.entity

data class Security(
        val securityId: Int,
        val securityLevel: Int,
        val twoFA: TwoFA,
        val identityVerification: IdentityVerification,
        val antiPishingCode: AntiPishingCode

)

data class TwoFA(
        val twoFAId: Int,
        val enable: Boolean,
        val googleAuthentication: GoogleAuthenticaton?,
        val smsAuthentication: SMSAuthentication?,
        val emailVerification: EmailVerification?
)

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
)

enum class State {
    PENDING, VERIFIED, BLOCKED
}

data class AntiPishingCode(
        val antiPishingCodeId: Int,
        val enable: Boolean,
        val code: String?
)