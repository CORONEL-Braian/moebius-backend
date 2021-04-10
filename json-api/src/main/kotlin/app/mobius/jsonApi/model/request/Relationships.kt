package app.mobius.jsonApi.model.request

data class Relationships(val relationships: List<Relationship>) {
    constructor() : this(relationships = listOf())
}

data class Relationship(val relationship: Map<String, Data>) {
    constructor() : this(relationship = mapOf())
}