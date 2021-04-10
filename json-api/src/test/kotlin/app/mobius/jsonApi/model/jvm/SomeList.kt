package app.mobius.jsonApi.model.jvm

data class SomeList(val list: Map<String, Any>) {
    constructor() : this(list = mapOf())
}