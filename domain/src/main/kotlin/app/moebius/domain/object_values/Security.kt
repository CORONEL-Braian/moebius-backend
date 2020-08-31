package app.moebius.domain.object_values

import java.util.*

data class Security(
        val securityUUID: UUID,
        val securityLevel: Int = 0,
        val securityMethods: SecurityMethods? = null
)

data class SecurityMethods(
        val securityMethodsUUID: UUID,
        val twoFA: TwoFA? = null,
        val identityVerification: IdentityVerification? = null,
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

data class IdentityVerification(
        val id: Int,
        val state: StatusIdentityVerification = StatusIdentityVerification.UNSOLICITED,
        val dni: DNI,
)

data class DNI(
        val dniUUID: UUID,
        val surname: String,
        val name: String,
        val sex: String,
        val nationality: Country,
        val ejemplar: String,
        val birthdate: Date,
        val dateIssue: Date,
        val dateExpiry: Date,
        val identificationNumber: Int
)

data class Country(
        val countryUUID: UUID,
        val name: String
)

enum class StatusIdentityVerification {
    UNSOLICITED, PENDING, VERIFIED, BLOCKED
}

data class AntiPishingCode(
        val antiPishingCodeUUID: UUID,
        val enable: Boolean,
        val code: String?
)