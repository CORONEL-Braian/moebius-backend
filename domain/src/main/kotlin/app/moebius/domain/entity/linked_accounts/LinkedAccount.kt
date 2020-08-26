package app.moebius.domain.entity.linked_accounts

import java.util.*

data class LinkedAccount(
        val linkedAccountUUID: UUID,
        val facebook: Facebook? = null
)

data class Facebook(
        val facebookUUID: UUID,
        val username: String
)