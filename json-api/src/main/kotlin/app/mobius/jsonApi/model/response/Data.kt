package app.mobius.jsonApi.model.response

import java.util.*

data class Data(
        val type: String,
        val id: UUID,
        val attributes: List<*>,
        val relationships: List<Relationship>,
        val links: Links
)

data class Relationship(
        val relationship: Map<String, RelationshipValue>
)

data class RelationshipValue(
        val links: Links,
        val datas: List<DataRelationship>
)

data class DataRelationship(
        val type: String,
        val id: UUID
)

