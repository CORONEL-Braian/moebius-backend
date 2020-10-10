package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
        @Id @GeneratedValue @Column(name = "accountUUID") val accountUUID: UUID? = null,
        val linkedAccounts: LinkedAccounts? = null,
)