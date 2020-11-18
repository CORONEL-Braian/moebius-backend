package app.mobius.infrastructure.model.request

import app.mobius.infrastructure.model.Attribute
import com.fasterxml.jackson.core.type.TypeReference
import java.util.*
import kotlin.collections.ArrayList

data class Data(
        val type: String,
//        val id: UUID? = null,
        val attributes: TypeReference<Attribute>,
        val relationships: List<Relationship>,
//        val links: Links
) {
    constructor() : this(type = "", attributes = listOf<Attribute>(), relationships = listOf())
}

data class Relationship(
        val relationship: Map<String, RelationshipValue>
)

data class RelationshipValue(
        val data: List<Data>
)