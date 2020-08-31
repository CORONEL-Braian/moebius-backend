package app.moebius.domain.object_values.linked_accounts

import java.util.*

interface LinkedAccount

data class Facebook(
        val facebookUUID: UUID,
        val username: String,
        val facebookId: Long? = null
): LinkedAccount