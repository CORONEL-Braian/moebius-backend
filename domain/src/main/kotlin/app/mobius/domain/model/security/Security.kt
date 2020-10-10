package app.mobius.domain.model.security

import app.mobius.domain.entity.StatusLiveness
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

/**
 * @param securityLevel: [0,4]
 */
@Entity
@Table(name = "security")
data class Security(
        @Id @GeneratedValue @Column(name = "security_uuid") val securityUUID: UUID? = null,
        val authentication: Authentication,
        val securityLevel: Int = 0,
        val securityMethods: SecurityMethods? = null,
)

/**
 * Represents a authentication credential
 * TODO: Add app token
 */
@Entity
@Table(name = "authentication")
data class Authentication(
        @Id @GeneratedValue @Column(name = "authentication_uuid") val authenticationUUID: UUID? = null,
        val session: Session,
        val basicAuth: BasicAuth
)

/**
 * Represents a traditional authentication
 * OBS: Other methods will not be considered
 */
@Entity
@Table(name = "traditional_credential")
data class BasicAuth(
        @Id @GeneratedValue @Column(name = "traditional_credential_uuid") val traditionalCredentialUUID: UUID? = null,
        val email: Email,
        val password: Password,
)

@Entity
@Table(name = "password")
data class Password(
        @Id @GeneratedValue @Column(name = "password_uuid") val passwordUUID: UUID? = null,
        val apiHashPassword: String,
        val dbHashPassword: String,
        val resetPasswordToken: String? = null,
        val resetPasswordTokenExpire: String? = null,
)

// TODO: Add more credentials

@Entity
@Table(name = "email")
data class Email(
        @Id @GeneratedValue @Column(name = "email_uuid") val emailUUID: UUID,

        @Column(name = "email") val email: String,

        @Enumerated(EnumType.STRING) @Column(name = "status_email") @Type(type = "pgsql_enum")
        val statusEmail: StatusEmail = StatusEmail.UNVERIFIED

)

enum class StatusEmail {
    UNVERIFIED, INVALID, VERIFIED
}