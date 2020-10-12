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
        val session: Session,
        val basicAuth: BasicAuth
) {
    constructor() : this()
}

/**
 * Represents a traditional authentication
 * OBS: Other methods will not be considered
 */
@Entity
@Table(name = "traditionalCredential")
data class BasicAuth(
        @Id @GeneratedValue @Column(name = "traditional_credentialUUID") val traditionalCredentialUUID: UUID? = null,
        val email: String,
        val password: Password,
) {
    constructor() : this()
}

@Entity
@Table(name = "password")
data class Password(
        @Id @GeneratedValue @Column(name = "passwordUUID") val passwordUUID: UUID? = null,
        val apiHashPassword: String,
        val dbHashPassword: String,
        val resetPasswordToken: String? = null,
        val resetPasswordTokenExpire: String? = null,
) {
    constructor() : this()
}

// TODO: Add more credentials

