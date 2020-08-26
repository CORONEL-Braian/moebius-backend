package app.moebius.domain.entity

import java.util.*

data class Security(
        val securityUUID: UUID,
        val securityLevel: Int = 0,
        val securityMethods: List<SecurityMethods>? = null
)

interface SecurityMethods

data class TwoFA(
        val twoFAUUID: UUID,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton?,
        val smsAuthentication: SMSAuthentication?,
        val emailVerification: EmailVerification?
): SecurityMethods

data class GoogleAuthenticaton(
        val googleAuthenticationUUID: UUID,
        val verificationCode: Int
)

data class SMSAuthentication(
        val smsAuthenticationUUID: UUID
)

data class EmailVerification(
        val emailVerificationUUID: UUID,
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
        val antiPishingCodeUUID: UUID,
        val enable: Boolean,
        val code: String?
): SecurityMethods