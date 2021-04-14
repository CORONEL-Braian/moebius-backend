package app.mobius.jsonApi.model.request

import java.util.*

/**
 * @param relationships: Represents the relationships with a custom name for each one
 *  - OBS: Does not split data type (from List<Map<X>> to List<Y>) for avoid using generics
 *         for define custom property names in each relationship.
 */
data class Data(
        val type: String,
        val id: UUID? = null,
        val attributes: Map<String, Any>,
        val relationships: List<Map<String, Data>>,

        val links: Links? = null
) {
    constructor() : this(type = "", attributes = mapOf(), relationships = listOf())
}