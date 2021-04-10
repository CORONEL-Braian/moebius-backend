package app.mobius.jsonApi.test.model.request

data class RelationshipsMock(val relationships: List<RelationshipMock>) {
    constructor() : this(relationships = listOf())
}

data class RelationshipMock(val anyRelationship: Map<String, DataAtomicMock>) {
    constructor() : this(anyRelationship = mapOf())
}