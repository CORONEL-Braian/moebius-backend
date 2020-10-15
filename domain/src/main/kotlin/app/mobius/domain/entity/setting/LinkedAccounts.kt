package app.mobius.domain.entity.setting

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "linkedAccounts")
data class LinkedAccounts(
        @Id @GeneratedValue @Column(name = "linkedAccounts_uuid") val linkedAccountUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "facebook_uuid", referencedColumnName = "facebook_uuid")
        val facebook: Facebook? = null
)

@Entity
@Table(name = "facebook")
data class Facebook(
        @Id @GeneratedValue @Column(name = "facebook_uuid") val facebookUUID: UUID? = null,
        @Column(name = "username") val username: String,
        @Column(name = "facebookId") val facebookId: Long? = null
) {
    constructor() : this(username = "")
}