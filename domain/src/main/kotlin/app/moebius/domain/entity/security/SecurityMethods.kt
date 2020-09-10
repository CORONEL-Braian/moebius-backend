package app.moebius.domain.entity.security

import java.util.*


/**
 *  Obs: Are manipulated by the identity
 */
data class SecurityMethods(
        val securityMethodsUUID: UUID,
        val identityVerification: IdentityVerification? = null,
        val twoFA: TwoFA? = null,
        val antiPishingCode: AntiPishingCode? = null,
        val keepSessionDaily: Boolean = false
)

data class TwoFA(
        val twoFAUUID: UUID,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton? = null,
        val smsAuthentication: SMSAuthentication? = null,
        val emailVerification: EmailVerification? = null
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