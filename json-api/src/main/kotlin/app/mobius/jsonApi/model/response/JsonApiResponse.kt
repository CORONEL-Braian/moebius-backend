package app.mobius.jsonApi.model.response

data class JsonApiResource(
        val links: Links,
        val data: List<Data>
)