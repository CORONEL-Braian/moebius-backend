package app.mobius.domain.model

import java.util.*

data class Metadata(
        val createdOn: Date,
        val updatedOn: Date,
        val deletedOn: Date,
        val rowVersion: Int
)