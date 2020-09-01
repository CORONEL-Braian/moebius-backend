package app.moebius.domain.entity

import app.moebius.domain.entity.security.Security
import java.util.*

data class Account(
        val accountUUID: UUID,
        val traditionalCredential: TraditionalCredential,
        val security: Security,
        val token: String,
        val linked: Linked? = null
)

data class TraditionalCredential(
        val traditionalCredentialUUID: UUID,
        val email: String,
        val password: Password,
)

data class Password(
        val passwordUUID: UUID,
        val apiHashPassword: String,
        val dbHashPassword: String,
        val resetPasswordToken: String,
        val resetPasswordTokenExpire: String,
)

// TODO: Add more credentials