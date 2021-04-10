package app.mobius.jsonApi.model.request

data class Links(
        val self: String? = null,
        val next: String? = null,
        val last: String? = null,
        val related: String? = null
)