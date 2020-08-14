package app.moebius.domain

data class Account(
        val accountId: Int,
        val email: String,
        val hashPassword: String,
        val isEmailConfirmed: Boolean,
        val emailConfirmationToken: String,
        val resetPasswordToken: String,
        val resetPasswordTokenExpire: String,
        val token: Int,
)
