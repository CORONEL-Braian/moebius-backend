package app.mobius.domain.entity.setting.security

import java.util.*
import javax.persistence.*

/**
 * @param securityLevel: [0,4]
 */
@Entity
@Table(name = "security")
data class Security(
        @Id @GeneratedValue @Column(name = "security_uuid") val securityUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "authentication_uuid", referencedColumnName = "authentication_uuid")
        val authentication: Authentication,

        @Column(name = "securityLevel")
        val securityLevel: Int = 0,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "securityMethods_uuid", referencedColumnName = "securityMethods_uuid", unique = true)
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
        @Id @GeneratedValue @Column(name = "authentication_uuid") val authenticationUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "session_uuid", referencedColumnName = "session_uuid", unique = true)
        val session: Session,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "basicAuth_uuid", referencedColumnName = "basicAuth_uuid", unique = true)
        val basicAuth: BasicAuth
) {
    constructor() : this(session = Session(), basicAuth = BasicAuth())
}

/**
 * Represents a traditional or basic authentication
 * OBS: Other methods will not be considered
 */
@Entity
@Table(name = "basicAuth")
data class BasicAuth(
        @Id @GeneratedValue @Column(name = "basicAuth_uuid") val basicAuthUUID: UUID? = null,
        @Column(name = "email") val email: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "password_uuid", referencedColumnName = "password_uuid")
        val password: Password,
) {
    constructor() : this(email = "", password = Password())
}

@Entity
@Table(name = "password")
data class Password(
        @Id @GeneratedValue @Column(name = "password_uuid") val passwordUUID: UUID? = null,
        @Column(name = "apiHashPassword") val apiHashPassword: String,
        @Column(name = "dbHashPassword") val dbHashPassword: String,
        @Column(name = "resetPasswordToken") val resetPasswordToken: String? = null,
        @Column(name = "resetPasswordTokenExpire") val resetPasswordTokenExpire: String? = null,
) {
    constructor() : this(apiHashPassword = "", dbHashPassword = "")
}