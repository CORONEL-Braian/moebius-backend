package app.mobius.domain.entity.security

import java.util.*

data class AppAuthSessionToken(
        val uuid: UUID,
        val token: String,
        val version: Byte
)