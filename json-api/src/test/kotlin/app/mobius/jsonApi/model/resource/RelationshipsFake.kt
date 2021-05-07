package app.mobius.jsonApi.model.resource

/**
 * Represents the relationships with a custom name for each one
 * OBS: Does not split data type (from Map<String, RelationshipData> to Map<Relationship>)
 * for avoid using generics for define custom property names in each relationship
 */
data class RelationshipsFake(val relationships: Map<String, RelationshipDataFake>) {
    constructor() : this(relationships = mapOf())
}

data class RelationshipFake(val anyRelationship: Map<String, RelationshipDataFake>) {
    constructor() : this(anyRelationship = mapOf())
}

/**
 * Cut the circular dependency
 * For not mock replace String by Data
 */
data class RelationshipDataFake(
        val data: String
) {
    constructor() : this(data = "")
}