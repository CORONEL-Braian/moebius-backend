package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "linked_accounts")
data class LinkedAccounts(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val linkedAccountUUID: UUID,
        val facebook: Facebook? = null
)

@Entity
@Table(name = "facebook")
data class Facebook(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id") val facebookUUID: UUID,
        val username: String,
        val facebookId: Long? = null
)