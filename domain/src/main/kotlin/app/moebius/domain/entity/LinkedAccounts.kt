package app.moebius.domain.entity

import java.util.*

data class Linked(
        val linkedAccountUUID: UUID,
        val facebook: Facebook
)

data class Facebook(
        val facebookUUID: UUID,
        val username: String,
        val facebookId: Long? = null
)