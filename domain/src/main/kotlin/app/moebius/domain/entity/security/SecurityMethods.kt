package app.moebius.domain.entity.security

import java.util.*
import javax.persistence.*


/**
 *  Obs: Are manipulated by the identity
 */
@Entity
@Table(name = "security_methods")
data class SecurityMethods(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "security_methods_uuid") val securityMethodsUUID: UUID,
        val identityVerification: IdentityVerification? = null,
        val twoFA: TwoFA? = null,
        val antiPishingCode: AntiPishingCode? = null,
        val keepSessionDaily: Boolean = false
)

@Entity
@Table(name = "two_factor_authentication")
data class TwoFA(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "two_factor_authentication_uuid") val twoFAUUID: UUID,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton? = null,
        val smsAuthentication: SMSAuthentication? = null,
        val emailVerification: EmailVerification? = null
)

@Entity
@Table(name = "google_authentication")
data class GoogleAuthenticaton(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "google_authentication_uuid") val googleAuthenticationUUID: UUID,
        val verificationCode: Int
)

@Entity
@Table(name = "sms_authentication")
data class SMSAuthentication(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "sms_authentication_uuid") val smsAuthenticationUUID: UUID,
        val verificationCode: Int
)

@Entity
@Table(name = "email_verification")
data class EmailVerification(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "email_verification_uuid") val emailVerificationUUID: UUID,
        val isEmailVerified: Boolean,
        val emailVerificationToken: String,
)

@Entity
@Table(name = "anti_pishing_code")
data class AntiPishingCode(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "anti_pishing_code_uuid") val antiPishingCodeUUID: UUID,
        val enable: Boolean,
        val code: String? = null
)