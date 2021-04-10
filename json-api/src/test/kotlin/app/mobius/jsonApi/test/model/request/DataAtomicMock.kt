package app.mobius.jsonApi.test.model.request

/**
 * For cut the circular dependency
 */
data class DataAtomicMock(
        val type: String
) {
    constructor() : this(type = "")
}