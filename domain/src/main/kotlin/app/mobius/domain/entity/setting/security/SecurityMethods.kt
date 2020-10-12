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
        @Id @GeneratedValue @Column(name = "securityMethodsUUID") val securityMethodsUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "identityVerificationUUID", referencedColumnName = "identityVerificationUUID")
        val identityVerification: IdentityVerification? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "twoFactorAuthtenticationUUID", referencedColumnName = "twoFactorAuthtenticationUUID")
        val TwoFactorAuth: TwoFactorAuth? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "antiPishingCodeUUID", referencedColumnName = "antiPishingCodeUUID")
        val antiPishingCode: AntiPishingCode? = null,
)

@Entity
@Table(name = "twoFactorAuthentication")
data class TwoFactorAuth(
        @Id @GeneratedValue @Column(name = "twoFactorAuthenticationUUID") val twoFAUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "googleAuthenticationUUID", referencedColumnName = "googleAuthenticationUUID")
        val googleAuthentication: GoogleAuthenticaton,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "smsAuthenticationUUID", referencedColumnName = "smsAuthenticationUUID")
        val smsAuthentication: SMSAuthentication,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "emailVerificationUUID", referencedColumnName = "emailVerificationUUID")
        val emailVerification: EmailVerification
) {
    constructor() : this(
            googleAuthentication = GoogleAuthenticaton(),
            smsAuthentication = SMSAuthentication(),
            emailVerification = EmailVerification())
}

@Entity
@Table(name = "googleAuthentication")
data class GoogleAuthenticaton(
        @Id @GeneratedValue @Column(name = "googleAuthenticationUUID") val googleAuthenticationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "verificationCode") val verificationCode: Int
) {
    constructor() : this(verificationCode = -1)
}

@Entity
@Table(name = "smsAuthentication")
data class SMSAuthentication(
        @Id @GeneratedValue @Column(name = "smsAuthenticationUUID") val smsAuthenticationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "verificationCode") val verificationCode: Int
) {
    constructor() : this(verificationCode = -1)
}

@Entity
@Table(name = "emailVerification")
data class EmailVerification(
        @Id @GeneratedValue @Column(name = "emailVerificationUUID") val emailVerificationUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,

        @Enumerated(EnumType.STRING) @Column(name = "emailStatus") @Type(type = "pgsql_enum")
        val emailVerificationStatus: EmailVerificationStatus = EmailVerificationStatus.UNCONFIRMED,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "tokenUUID", referencedColumnName = "tokenUUID")
        val token: Token? = null
)

enum class EmailVerificationStatus {
    UNCONFIRMED, INVALID, CONFIRMED
}

@Entity
@Table(name = "antiPishingCode")
data class AntiPishingCode(
        @Id @GeneratedValue @Column(name = "antiPishingCodeUUID") val antiPishingCodeUUID: UUID? = null,
        @Column(name = "enable") val enable: Boolean = false,
        @Column(name = "code") val code: String
) {
    constructor() : this(enable = false, code = "")
}