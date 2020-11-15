package app.mobius.infrastructure.model.request

import app.mobius.infrastructure.model.Attribute
import java.util.*

data class DataItem(
        val type: String,
        val id: UUID? = null,
        val attributes: List<Attribute>,
        val relationships: List<Relationship>,
        val links: Links
)

data class Relationship(
        val relationship: Map<String, RelationshipValue>
)

data class RelationshipValue(
        val datas: List<DataRelationship>
)

data class DataRelationship(
        val type: String,
        val id: UUID
)

