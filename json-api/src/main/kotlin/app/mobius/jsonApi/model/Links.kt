package app.mobius.jsonApi.model

/**
 * @param self: that identifies the resource represented by the resource object.
 */
data class Links(
        val self: String? = null,
        val next: String? = null,
        val last: String? = null,
        val related: String? = null
)