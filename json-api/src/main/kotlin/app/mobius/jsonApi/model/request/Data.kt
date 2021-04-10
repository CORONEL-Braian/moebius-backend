package app.mobius.jsonApi.model.request

import java.util.*

data class Data(
        val type: String,
        val id: UUID? = null,
        val attributes: Map<String, Any>,
        val relationships: List<Relationship>,
        val links: Links? = null
) {
    constructor() : this(type = "", attributes = mapOf(), relationships = listOf())
}