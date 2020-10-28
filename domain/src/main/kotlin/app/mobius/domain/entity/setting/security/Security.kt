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

        val securityLevel: Int = 0,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "security_methods_uuid", referencedColumnName = "security_methods_uuid", unique = true)
        val securityMethods: SecurityMethods? = null,
) {
    constructor() : this(authentication = Authentication())
}