package app.mobius.infrastructure.model.response

data class JsonApiResource(
        val links: Links,
        val data: List<Data>
)