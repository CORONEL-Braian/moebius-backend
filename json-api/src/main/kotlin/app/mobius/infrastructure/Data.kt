package app.mobius.infrastructure

import java.util.*

data class DataItem(
        val type: String,
        val uuid: UUID,
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
        val uuid: UUID
)

