package app.mobius.infrastructure.model.response

data class Links(
        val self: String? = null,
        val next: String? = null,
        val last: String? = null,
        val related: String? = null
)