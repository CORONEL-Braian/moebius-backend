package app.mobius.jsonApi.model

/**
 * "included" of json api specification is not used. The hierarchy is in data -> relationships relationships
 */
data class JsonApiResource(
        val links: Links? = null,
        val data: List<ResourceData>
) {
    constructor() : this(links = Links(), data = listOf())
}