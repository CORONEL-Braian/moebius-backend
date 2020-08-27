package app.moebius.domain.model.linked_accounts

import java.util.*

interface LinkedAccount

data class Facebook(
        val facebookUUID: UUID,
        val username: String,
        val facebookId: Long? = null
): LinkedAccount