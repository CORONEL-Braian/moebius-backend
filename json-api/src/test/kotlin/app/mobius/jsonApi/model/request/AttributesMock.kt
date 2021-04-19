package app.mobius.jsonApi.model.request

data class AttributesMock(val attributes: Map<String, Any>) {
    constructor() : this(attributes = mapOf())
}