package app.mobius.infrastructure.model.request

data class JsonApiRequest(
        val data: List<Data>,   // TODO: Check if use ArrayList exactly when deserialize
) {
    constructor() : this(data = listOf())
}