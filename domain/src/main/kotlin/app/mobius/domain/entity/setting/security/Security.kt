package app.mobius.domain.entity.setting.security

import java.util.*
import javax.persistence.*

/**
 * @param securityLevel: [0,4]
 */
@Entity
@Table(name = "security")
data class Security(
        @Id @GeneratedValue @Column(name = "securityUUID") val securityUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "authenticationUUID", referencedColumnName = "authenticationUUID")
        val authentication: Authentication,

        @Column(name = "securityLevel")
        val securityLevel: Int = 0,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "securityMethodsUUID", referencedColumnName = "securityMethodsUUID", unique = true)
        val securityMethods: SecurityMethods? = null,
) {
    constructor() : this(authentication = Authentication())
}

/**
 * Represents a authentication credential
 * TODO: Add app token
 */
@Entity
@Table(name = "authentication")
data class Authentication(
        @Id @GeneratedValue @Column(name = "authenticationUUID") val authenticationUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "sessionUUID", referencedColumnName = "sessionUUID", unique = true)
        val session: Session,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "basicAuthUUID", referencedColumnName = "basicAuthUUID", unique = true)
        val basicAuth: BasicAuth
) {
    constructor() : this(session = Session(), basicAuth = BasicAuth())
}

/**
 * Represents a traditional or basic authentication
 * OBS: Other methods will not be considered
 */
@Entity
@Table(name = "BasicAuth")
data class BasicAuth(
        @Id @GeneratedValue @Column(name = "traditional_credentialUUID") val traditionalCredentialUUID: UUID? = null,
        @Column(name = "email") val email: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "passwordUUID", referencedColumnName = "passwordUUID")
        val password: Password,
) {
    constructor() : this(email = "", password = Password())
}

@Entity
@Table(name = "password")
data class Password(
        @Id @GeneratedValue @Column(name = "passwordUUID") val passwordUUID: UUID? = null,
        @Column(name = "apiHashPassword") val apiHashPassword: String,
        @Column(name = "dbHashPassword") val dbHashPassword: String,
        @Column(name = "resetPasswordToken") val resetPasswordToken: String? = null,
        @Column(name = "resetPasswordTokenExpire") val resetPasswordTokenExpire: String? = null,
) {
    constructor() : this(apiHashPassword = "", dbHashPassword = "")
}