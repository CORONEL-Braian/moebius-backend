package app.mobius.infrastructure.model.response

import app.mobius.infrastructure.model.Attribute
import java.util.*

data class Included(
        val type: String,
        val uuid: UUID,
        val attributes: List<Attribute>,
        val links: Links
)
