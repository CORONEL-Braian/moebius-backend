package app.mobius.jsonApi.test.model.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class RelationshipsMock(val relationships: List<RelationshipMock>) {
    constructor() : this(relationships = listOf())
}

//TODO: Change property name in runtime
data class RelationshipMock(
//        val anyRelationship: Map<String, DataAtomicMock>
        @JsonProperty(value = "otherName") val anyRelationship: Map<String, DataAtomicMock>
) {
    constructor() : this(anyRelationship = mapOf())
}