package app.moebius.domain.model.linked_accounts

import java.util.*

data class LinkedAccount(
        val linkedAccountUUID: Int,
        val facebook: Facebook? = null
)

data class Facebook(
        val facebookUUID: UUID,
        val facebookUUID: Int,
        val username: String
)