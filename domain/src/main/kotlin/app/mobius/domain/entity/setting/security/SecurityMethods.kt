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
        @Id @GeneratedValue @Column(name = "security_methodsUUID") val securityMethodsUUID: UUID? = null,
        val identityVerification: IdentityVerification? = null,
        val twoFA: TwoFA? = null,
        val antiPishingCode: AntiPishingCode? = null,
        val keepSessionDaily: Boolean = false
) {
    constructor() : this()
}

@Entity
@Table(name = "two_factor_authentication")
data class TwoFA(
        @Id @GeneratedValue @Column(name = "two_factor_authenticationUUID") val twoFAUUID: UUID? = null,
        val enable: Boolean = false,
        val googleAuthentication: GoogleAuthenticaton? = null,
        val smsAuthentication: SMSAuthentication? = null,
        val emailVerification: EmailVerification? = null
) {
    constructor() : this()
}

@Entity
@Table(name = "google_authentication")
data class GoogleAuthenticaton(
        @Id @GeneratedValue @Column(name = "google_authenticationUUID") val googleAuthenticationUUID: UUID? = null,
        val verificationCode: Int
) {
    constructor() : this()
}

@Entity
@Table(name = "sms_authentication")
data class SMSAuthentication(
        @Id @GeneratedValue @Column(name = "sms_authenticationUUID") val smsAuthenticationUUID: UUID? = null,
        val verificationCode: Int
) {
    constructor() : this()
}

@Entity
@Table(name = "email_verification")
data class EmailVerification(
        @Id @GeneratedValue @Column(name = "email_verificationUUID") val emailVerificationUUID: UUID? = null,

        @Enumerated(EnumType.STRING) @Column(name = "status_email") @Type(type = "pgsql_enum")
        val emailStatus: EmailStatus = EmailStatus.UNCONFIRMED,

        val emailVerificationToken: String,
) {
    constructor() : this()
}

enum class EmailStatus {
    UNCONFIRMED, INVALID, CONFIRMED
}

@Entity
@Table(name = "anti_pishing_code")
data class AntiPishingCode(
        @Id @GeneratedValue @Column(name = "anti_pishing_codeUUID") val antiPishingCodeUUID: UUID? = null,
        val enable: Boolean,
        val code: String? = null
) {
    constructor() : this()
}