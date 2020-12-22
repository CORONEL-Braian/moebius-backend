package app.mobius.domain.entity

import java.util.*

data class AppAuthSessionToken(
        val uuid: UUID,
        val token: String,
        val version: Byte
)