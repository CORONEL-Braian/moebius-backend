package app.mobius.jsonApi.model.request

import java.util.*

/**
 * @param relationships: Represents the relationships with a custom name for each one
 *  - OBS: Does not split data type (from Map<String, RelationshipData> to Map<Relationship>)
 *         for avoid using generics and define custom property names in each relationship.
 */
data class RequestData(
        val type: String,
        val id: UUID? = null,
        val attributes: Map<String, Any>,
        val relationships: Map<String, RelationshipData>,
        val links: Links? = null
) {
    constructor() : this(type = "", attributes = mapOf(), relationships = mapOf())
}

data class RelationshipData(val data: RequestData) {
    constructor() : this(data = RequestData())
}