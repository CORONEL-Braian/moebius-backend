package app.mobius.domain.entity.setting

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
        @Id @GeneratedValue @Column(name = "accountUUID") val accountUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "linkedAccountsUUID", referencedColumnName = "linkedAccountsUUID")
        val linkedAccounts: LinkedAccounts? = null,
)