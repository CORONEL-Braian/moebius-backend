package app.mobius.infrastructure.model.request

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

data class Relationship(
        val relationship: Map<String, RelationshipValue>
)

data class RelationshipValue(
        val data: List<Data>
)