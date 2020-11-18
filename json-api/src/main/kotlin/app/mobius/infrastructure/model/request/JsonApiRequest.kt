package app.mobius.infrastructure.model.request

data class JsonApiRequest(
        val data: List<Data>,
) {
    constructor() : this(data = listOf())
}