package app.mobius.jsonApi.model.request

data class JsonApiRequest(
        val data: List<Data>,
) {
    constructor() : this(data = listOf())
}