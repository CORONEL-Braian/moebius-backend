package app.mobius.domain.entity.setting

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
        @Id @GeneratedValue @Column(name = "account_uuid") val accountUUID: UUID? = null,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "linkedAccounts_uuid", referencedColumnName = "linkedAccounts_uuid")
        val linkedAccounts: LinkedAccounts? = null,
)