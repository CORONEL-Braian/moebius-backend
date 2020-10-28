package app.mobius.domain.entity.setting.security

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/**
 *  Obs: Are manipulated by the person
 */
@Entity
@Table(name = "security_methods")
data class SecurityMethods(
        @Id @GeneratedValue @Column(name = "security_methods_uuid") val securityMethodsUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "identity_verification_uuid", referencedColumnName = "identity_verification_uuid")
        val identityVerification: IdentityVerification? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "two_factor_authentication_uuid", referencedColumnName = "two_factor_authentication_uuid")
        val twoFactorAuth: TwoFactorAuth? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "anti_pishing_code_uuid", referencedColumnName = "anti_pishing_code_uuid")
        val antiPishingCode: AntiPishingCode? = null,
)

@Entity
@Table(name = "two_factor_authentication")
data class TwoFactorAuth(
        @Id @GeneratedValue @Column(name = "two_factor_authentication_uuid") val twoFAUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "google_authentication_uuid", referencedColumnName = "google_authentication_uuid")
        val googleAuthentication: GoogleAuth,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "sms_authentication_uuid", referencedColumnName = "sms_authentication_uuid")
        val smsAuthentication: SMSAuthentication,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "email_verification_uuid", referencedColumnName = "email_verification_uuid")
        val emailVerification: EmailVerification
) {
    constructor() : this(
            googleAuthentication = GoogleAuth(),
            smsAuthentication = SMSAuthentication(),
            emailVerification = EmailVerification())
}

@Entity
@Table(name = "google_authentication")
data class GoogleAuth(
        @Id @GeneratedValue @Column(name = "google_authentication_uuid") val googleAuthenticationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "verification_code") val verificationCode: Int
) {
    constructor() : this(verificationCode = -1)
}

@Entity
@Table(name = "sms_authentication")
data class SMSAuthentication(
        @Id @GeneratedValue @Column(name = "sms_authentication_uuid") val smsAuthenticationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "verification_code") val verificationCode: Int
) {
    constructor() : this(verificationCode = -1)
}

@Entity
@Table(name = "email_verification")
data class EmailVerification(
        @Id @GeneratedValue @Column(name = "email_verification_uuid") val emailVerificationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,

        @Enumerated(EnumType.STRING) @Type(type = "pgsql_enum")
        val emailVerificationStatus: EmailVerificationStatus = EmailVerificationStatus.UNCONFIRMED,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "token_uuid", referencedColumnName = "token_uuid")
        val token: Token? = null
) {
    constructor() : this(token = Token())
}

enum class EmailVerificationStatus {
    UNCONFIRMED, INVALID, PENDING, CONFIRMED
}

@Entity
@Table(name = "anti_pishing_code")
data class AntiPishingCode(
        @Id @GeneratedValue @Column(name = "anti_pishing_code_uuid") val antiPishingCodeUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "code") val code: String
) {
    constructor() : this(enable = false, code = "")
}