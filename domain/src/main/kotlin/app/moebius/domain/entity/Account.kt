package app.moebius.domain.entity

data class Account(
        val accountId: Int,
        val credential: Credential,
        val security: Security,
        val token: Int,
)

data class Credential(
        val credentialId: Int,
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
