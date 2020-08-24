package app.moebius.domain.model.linked_accounts

import java.util.*

data class LinkedAccount(
        val linkedAccountId: Int,
        val facebook: Facebook? = null
)

data class Facebook(
        val facebookUUID: UUID,
        val facebookId: Int,
        val username: String
)