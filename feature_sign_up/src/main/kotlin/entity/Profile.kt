package entity

data class Profile(
        val profileId: Int,
        val dni: Int,
        val identificationNumber: Int,
        val name: String,
        val surname: String,
        val facebookId: Int,
        val originLocality: String,
        val phone: Int,
        val birthdate: String,
        val nationality: String,
        val sex: String,
        val avatarUrl: String,
        val isIdentityValidated: String,
)

