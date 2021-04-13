package app.mobius.jsonApi.model.request

import java.util.*

data class Data(
        val type: String,
        val id: UUID? = null,
        val attributes: Map<String, Any>,
        val relationships: Relationships,   //TODO: Check if use explicit data type of Relationships
        val links: Links? = null
) {
    constructor() : this(type = "", attributes = mapOf(), relationships = Relationships())
}