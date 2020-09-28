package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "linked_accounts")
data class LinkedAccounts(
        @Id @GeneratedValue @Column(name = "linked_accounts_uuid") val linkedAccountUUID: UUID,
        val facebook: Facebook? = null
)

@Entity
@Table(name = "facebook")
data class Facebook(
        @Id @GeneratedValue @Column(name = "facebook_uuid") val facebookUUID: UUID,
        val username: String,
        val facebookId: Long? = null
)