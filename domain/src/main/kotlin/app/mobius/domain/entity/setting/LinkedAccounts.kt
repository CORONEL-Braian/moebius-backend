package app.mobius.domain.entity.setting

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LinkedAccounts")
data class LinkedAccounts(
        @Id @GeneratedValue @Column(name = "linked_accountsUUID") val linkedAccountUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "facebookUUID", referencedColumnName = "facebookUUID")
        val facebook: Facebook? = null
)

@Entity
@Table(name = "Facebook")
data class Facebook(
        @Id @GeneratedValue @Column(name = "facebookUUID") val facebookUUID: UUID? = null,
        @Column(name = "username") val username: String,
        @Column(name = "facebookId") val facebookId: Long? = null
) {
    constructor() : this(username = "")
}