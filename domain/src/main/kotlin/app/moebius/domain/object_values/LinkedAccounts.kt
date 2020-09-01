package app.moebius.domain.object_values

import java.util.*

data class Linked(
        val linkedAccountUUID: UUID,
        val facebook: Facebook? = null
)

data class Facebook(
        val facebookUUID: UUID,
        val username: String,
        val facebookId: Long? = null
)