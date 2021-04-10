package app.mobius.jsonApi.model.request

data class AttributesTest(val attributes: Map<String, Any>) {
    constructor() : this(attributes = mapOf())
}