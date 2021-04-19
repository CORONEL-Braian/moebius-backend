package app.mobius.jsonApi.model.jvm

data class SomeList(val map: Map<String, Any>) {
    constructor() : this(map = mapOf())
}