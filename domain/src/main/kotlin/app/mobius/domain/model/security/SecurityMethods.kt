package app.mobius.domain.model.security

import java.util.*
import javax.persistence.*


/**
 *  Obs: Are manipulated by the identity
 */
@Entity
@Table(name = "security_methods")
data class SecurityMethods(
        @Id @GeneratedValue @Column(name = "security_methods_uuid") val securityMethodsUUID: UUID? = null,
        val identityVerification: IdentityVerification? = null,
        val twoFA: TwoFA? = null,
        val antiPishingCode: AntiPishingCode? = null,
        val keepSessionDaily: Boolean = false
)

@Entity
@Table(name = "two_factor_authentication")
data class TwoFA(
        @Id @GeneratedValue @Column(name = "two_factor_authentication_uuid") val twoFAUUID: UUID? = null,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton? = null,
        val smsAuthentication: SMSAuthentication? = null,
        val emailVerification: EmailVerification? = null
)

@Entity
@Table(name = "google_authentication")
data class GoogleAuthenticaton(
        @Id @GeneratedValue @Column(name = "google_authentication_uuid") val googleAuthenticationUUID: UUID? = null,
        val verificationCode: Int
)

@Entity
@Table(name = "sms_authentication")
data class SMSAuthentication(
        @Id @GeneratedValue @Column(name = "sms_authentication_uuid") val smsAuthenticationUUID: UUID? = null,
        val verificationCode: Int
)

@Entity
@Table(name = "email_verification")
data class EmailVerification(
        @Id @GeneratedValue @Column(name = "email_verification_uuid") val emailVerificationUUID: UUID? = null,
        val isEmailVerified: Boolean,
        val emailVerificationToken: String,
)

@Entity
@Table(name = "anti_pishing_code")
data class AntiPishingCode(
        @Id @GeneratedValue @Column(name = "anti_pishing_code_uuid") val antiPishingCodeUUID: UUID? = null,
        val enable: Boolean,
        val code: String? = null
)