package app.mobius.domain.entity.setting

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "linked_account")
data class LinkedAccount(
        @Id @GeneratedValue @Column(name = "linked_account_uuid") val linkedAccountUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "facebook_uuid", referencedColumnName = "facebook_uuid")
        val facebook: Facebook? = null
)

@Entity
@Table(name = "facebook")
data class Facebook(
        @Id @GeneratedValue @Column(name = "facebook_uuid") val facebookUUID: UUID? = null,
        val username: String,
        @Column(name = "facebook_id") val facebookId: Long? = null
) {
    constructor() : this(username = "")
}