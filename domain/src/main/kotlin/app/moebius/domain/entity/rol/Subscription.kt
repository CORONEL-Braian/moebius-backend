package app.moebius.domain.entity.rol

import java.util.*

data class Subscription(
        val subscriptionUUID: UUID,
        val name: Type = Type.BASIC
)

enum class Type {
    BASIC, GOLD, PREMIUM
}