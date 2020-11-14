package app.mobius.infrastructure

import java.util.*

data class Included(
        val type: String,
        val uuid: UUID,
        val attributes: List<Attribute>,
        val links: Links
)

data class Attribute(
        val attribute: Map<String, *>
)

