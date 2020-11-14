package app.mobius.infrastructure

data class ResponseJsonApi(
        val links: Links,
        val data: List<DataItem>,
        val included: List<Included>
)