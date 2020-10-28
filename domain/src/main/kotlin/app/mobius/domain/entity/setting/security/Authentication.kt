package app.mobius.domain.entity.setting.security

import org.hibernate.annotations.Generated
import org.hibernate.annotations.GenerationTime
import java.util.*
import javax.persistence.*


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
        val email: String,

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
        @Column(name = "api_hash_pw") val apiHashPassword: String,
        @Generated(GenerationTime.INSERT) @Column(name = "db_hash_pw") val dbHashPassword: String? = null,
        @Column(name = "reset_pw_token") val resetPasswordToken: String? = null,
        @Column(name = "reset_pw_token_expire") val resetPasswordTokenExpire: Date? = null,
) {
    constructor() : this(apiHashPassword = "-1")
}