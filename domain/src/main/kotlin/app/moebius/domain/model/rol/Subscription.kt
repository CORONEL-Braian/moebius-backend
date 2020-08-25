package app.moebius.domain.model.rol

data class Subscription(
        val subscriptionUUID: Int,
        val name: Type = Type.BASIC
)

enum class Type {
    BASIC, GOLD, PREMIUM
}