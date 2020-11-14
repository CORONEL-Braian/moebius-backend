package app.mobius.infrastructure

data class JsonApi<T>(
        val links: Links,
        val data: Data,
        val included: Included
)