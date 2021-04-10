package app.mobius.jsonApi.test.model.request

data class AttributesMock(val attributes: Map<String, Any>) {
    constructor() : this(attributes = mapOf())
}