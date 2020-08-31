package app.moebius.domain.entity

import app.moebius.domain.object_values.linked_accounts.LinkedAccount
import java.util.*

data class Account(
        val accountUUID: UUID,
        val credential: TraditionalCredential,
        val security: Security,
        val token: String,
        val linkedAccounts: List<LinkedAccount>? = null
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