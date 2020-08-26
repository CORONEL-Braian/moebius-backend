package app.moebius.domain.model.rol

import java.util.*

data class Subscription(
        val subscriptionUUID: UUID,
        val name: Type = Type.BASIC
)

enum class Type {
    BASIC, GOLD, PREMIUM
}