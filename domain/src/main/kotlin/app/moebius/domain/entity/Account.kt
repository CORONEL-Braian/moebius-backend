package app.moebius.domain.entity

import app.moebius.domain.model.linked_accounts.LinkedAccount
import java.util.*

data class Account(
        val accountUUID: UUID,
        val credential: Credential,
        val security: Security,
        val token: String,
        val linkedAccounts: List<LinkedAccount>? = null
)

data class Credential(
        val credentialUUID: UUID,
        val email: String,
        val password: Password,
)

data class Password(
        val passwordUUID: UUID,
        val hashPassword: String,
        val resetPasswordToken: String,
        val resetPasswordTokenExpire: String,
)

// TODO: Add more credentials
