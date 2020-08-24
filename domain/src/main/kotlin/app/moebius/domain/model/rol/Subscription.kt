package app.moebius.domain.model.rol

data class Subscription(
        val subscriptionId: Int,
        val name: Type = Type.BASIC
)

enum class Type {
    BASIC, GOLD, PREMIUM
}