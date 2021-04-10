package app.mobius.jsonApi.test.model.jvm

data class SomeList(val list: Map<String, Any>) {
    constructor() : this(list = mapOf())
}