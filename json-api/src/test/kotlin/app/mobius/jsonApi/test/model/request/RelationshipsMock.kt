package app.mobius.jsonApi.test.model.request

import app.mobius.jsonApi.model.request.Data
import app.mobius.jsonApi.model.request.Relationship
import app.mobius.jsonApi.model.request.RelationshipValue

data class RelationshipsMock(val relationships: List<Relationship>) {
    constructor() : this(relationships = listOf())
}

data class RelationshipMock(val relationship: Map<String, RelationshipValue>) {
    constructor() : this(relationship = mapOf())
}

data class RelationshipValueWithStringMock(val data: List<String>) {
    constructor()  : this(data = listOf())
}

data class RelationshipValueMock(val data: List<Data>) {
    constructor()  : this(data = listOf())
}