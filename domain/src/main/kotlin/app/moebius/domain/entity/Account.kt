package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "account")
data class Account(
        val accountUUID: UUID,
        val linkedAccounts: LinkedAccounts? = null
)