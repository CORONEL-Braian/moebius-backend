package app.mobius.jsonApi.model.request

import com.fasterxml.jackson.annotation.JsonProperty

//TODO: Change property name in runtime
data class Relationship(val anyRelationship: Map<String, Data>) {
    constructor() : this(anyRelationship = mapOf())
}