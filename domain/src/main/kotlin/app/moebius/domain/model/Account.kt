package app.moebius.domain.model

import app.moebius.domain.model.linked_accounts.LinkedAccount

data class Account(
        val accountId: Int,
        val credential: Credential,
        val security: Security,
        val token: Int,
        val linkedAccounts: List<LinkedAccount>? = null
)

data class Credential(
        val email: String,
        val password: Password,
)

data class Password(
        val passwordId: Int,
        val hashPassword: String,
        val resetPasswordToken: String,
        val resetPasswordTokenExpire: String,
)

// TODO: Add more credentials