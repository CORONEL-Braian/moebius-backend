package app.mobius.jsonApi.model.request

data class Relationship(val relationship: Map<String, Data>) {
    constructor() : this(relationship = mapOf())
}