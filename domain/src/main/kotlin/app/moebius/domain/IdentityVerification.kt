package app.moebius.domain

data class IdentityVerification(
        val id: Int,
        val state: State,
)

enum class State {
    PENDING, COMPLETED, BLOCKED
}