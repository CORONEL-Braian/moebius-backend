package app.moebius.domain.object_values.security

import java.util.*

data class Security(
        val securityUUID: UUID,
        val securityLevel: Int = 0,
        val securityMethods: SecurityMethods? = null
)

data class SecurityMethods(
        val securityMethodsUUID: UUID,
        val identityVerification: IdentityVerification? = null,
        val twoFA: TwoFA? = null,
        val antiPishingCode: AntiPishingCode? = null
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
        val code: String?
)