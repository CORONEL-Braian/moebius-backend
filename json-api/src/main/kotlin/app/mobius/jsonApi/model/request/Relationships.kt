package app.mobius.jsonApi.model.request

data class Relationship(val anyRelationship: Map<String, Data>) {
    constructor() : this(anyRelationship = mapOf())
}