package app.mobius.jsonApi.model.request

/**
 * Represents the relationships with a custom name for each one
 * OBS: Does not split data type (from List<Map<X>> to List<Y>)
 * for avoid using generics for define custom property names in each relationship
 */
data class Relationships(val relationships: Map<String, Data>) {
    constructor() : this(relationships = mapOf())
}