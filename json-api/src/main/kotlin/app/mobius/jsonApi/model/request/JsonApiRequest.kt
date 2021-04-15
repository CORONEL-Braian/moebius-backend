package app.mobius.jsonApi.model.request

data class JsonApiRequest(
        val data: List<RequestData>,
) {
    constructor() : this(data = listOf())
}