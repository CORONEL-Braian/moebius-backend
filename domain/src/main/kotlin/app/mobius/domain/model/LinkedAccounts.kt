package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "linked_accounts")
data class LinkedAccounts(
        @Id @GeneratedValue @Column(name = "linked_accountsUUID") val linkedAccountUUID: UUID? = null,
        val facebook: Facebook? = null
)

@Entity
@Table(name = "facebook")
data class Facebook(
        @Id @GeneratedValue @Column(name = "facebookUUID") val facebookUUID: UUID? = null,
        val username: String,
        val facebookId: Long? = null
)