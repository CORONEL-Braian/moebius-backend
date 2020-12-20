package app.mobius.domain.entity

import java.time.LocalDateTime
import java.util.*

data class AppGlobalSessionToken(
        val uuid: UUID,
        val token: String,
        val expiry: LocalDateTime
)