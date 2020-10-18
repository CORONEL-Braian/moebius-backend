package app.mobius.domain.entity.setting.security

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/**
 *  Obs: Are manipulated by the person
 */
@Entity
@Table(name = "securityMethods")
data class SecurityMethods(
        @Id @GeneratedValue @Column(name = "securityMethods_uuid") val securityMethodsUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "identity_verification_uuid", referencedColumnName = "identity_verification_uuid")
        val identityVerification: IdentityVerification? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "twoFactorAuthentication_uuid", referencedColumnName = "twoFactorAuthentication_uuid")
        val TwoFactorAuth: TwoFactorAuth? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "antiPishingCode_uuid", referencedColumnName = "antiPishingCode_uuid")
        val antiPishingCode: AntiPishingCode? = null,
)

@Entity
@Table(name = "twoFactorAuthentication")
data class TwoFactorAuth(
        @Id @GeneratedValue @Column(name = "twoFactorAuthentication_uuid") val twoFAUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "googleAuthentication_uuid", referencedColumnName = "googleAuthentication_uuid")
        val googleAuthentication: GoogleAuth,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "smsAuthentication_uuid", referencedColumnName = "smsAuthentication_uuid")
        val smsAuthentication: SMSAuthentication,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "emailVerification_uuid", referencedColumnName = "emailVerification_uuid")
        val emailVerification: EmailVerification
) {
    constructor() : this(
            googleAuthentication = GoogleAuth(),
            smsAuthentication = SMSAuthentication(),
            emailVerification = EmailVerification())
}

@Entity
@Table(name = "googleAuthentication")
data class GoogleAuth(
        @Id @GeneratedValue @Column(name = "googleAuthentication_uuid") val googleAuthenticationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "verificationCode") val verificationCode: Int
) {
    constructor() : this(verificationCode = -1)
}

@Entity
@Table(name = "smsAuthentication")
data class SMSAuthentication(
        @Id @GeneratedValue @Column(name = "smsAuthentication_uuid") val smsAuthenticationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "verificationCode") val verificationCode: Int
) {
    constructor() : this(verificationCode = -1)
}

@Entity
@Table(name = "emailVerification")
data class EmailVerification(
        @Id @GeneratedValue @Column(name = "emailVerification_uuid") val emailVerificationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,

        @Enumerated(EnumType.STRING) @Column(name = "emailStatus") @Type(type = "pgsql_enum")
        val emailVerificationStatus: EmailVerificationStatus = EmailVerificationStatus.UNCONFIRMED,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "token_uuid", referencedColumnName = "token_uuid")
        val token: Token? = null
)

enum class EmailVerificationStatus {
    UNCONFIRMED, INVALID, CONFIRMED
}

@Entity
@Table(name = "antiPishingCode")
data class AntiPishingCode(
        @Id @GeneratedValue @Column(name = "antiPishingCode_uuid") val antiPishingCodeUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "code") val code: String
) {
    constructor() : this(enable = false, code = "")
}