package app.mobius.infrastructure.model.request

import app.mobius.infrastructure.model.Attribute
import java.util.*

data class Data(
        val type: String,
//        val id: UUID? = null,
        val attributes: List<Attribute>,
        val relationships: List<Relationship>,
        val links: Links
)

data class Relationship(
        val relationship: Map<String, RelationshipValue>
)

data class RelationshipValue(
        val data: List<Data>
)