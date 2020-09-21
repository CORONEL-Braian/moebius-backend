package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "linked_accounts")
data class LinkedAccounts(
        @Id val linkedAccountUUID: UUID,
        val facebook: Facebook? = null
)

@Entity
@Table(name = "facebook")
data class Facebook(
        @Id val facebookUUID: UUID,
        val username: String,
        val facebookId: Long? = null
)