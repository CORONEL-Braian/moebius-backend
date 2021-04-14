package app.mobius.jsonApi.test.model.request

/**
 * Cut the circular dependency
 * For not mock replace String by Data
 */
data class DataAtomicMock(
        val data: String = ""
) {
    constructor() : this(data = "")
}