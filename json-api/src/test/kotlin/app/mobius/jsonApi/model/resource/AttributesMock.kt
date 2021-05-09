package app.mobius.jsonApi.model.resource

data class AttributesMock(val attributes: Map<String, Any>) {
    constructor() : this(attributes = mapOf())
}