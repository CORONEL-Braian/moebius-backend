package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "account")
data class Account(
        @Id val accountUUID: UUID,
        val linkedAccounts: LinkedAccounts? = null,
)