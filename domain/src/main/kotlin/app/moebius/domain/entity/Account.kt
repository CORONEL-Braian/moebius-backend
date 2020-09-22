package app.moebius.domain.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "account_uuid") val accountUUID: UUID,
        val linkedAccounts: LinkedAccounts? = null,
)