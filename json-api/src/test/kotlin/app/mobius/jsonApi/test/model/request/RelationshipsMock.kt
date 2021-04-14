package app.mobius.jsonApi.test.model.request

/**
 * Represents the relationships with a custom name for each one
 * OBS: Does not split data type (from List<Map<X>> to List<Y>)
 * for avoid using generics for define custom property names in each relationship
 */
data class RelationshipsMock(val relationships: List<Map<String, DataAtomicMock>>) {
    constructor() : this(relationships = listOf())
}

data class RelationshipMock(val anyRelationship: Map<String, DataAtomicMock>) {
    constructor() : this(anyRelationship = mapOf())
}